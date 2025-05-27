package com.example.zippickT.domain.gpt.service;


import com.example.zippickT.domain.User.entity.Member;
import com.example.zippickT.domain.User.exception.UserNotFoundException;
import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.gpt.web.dto.GptSimilarUserReq;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserReq;
import com.example.zippickT.domain.house.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {

    private final UserRepository userRepository;
    private final UserHouseInfoRepository userHouseInfoRepository;
    private final RestTemplate restTemplate;

    @Value("${openai.api.url}")
    private String gptUrl;


    @Override
    public List<SimilarUserReq> getSimilarUser(Long userId) {

        //해당 사용자가 있는지 검증 후 해당 사용자 데이터 member에 저장
        Member member = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        //해당 사용자를 제외한 모든 사용자를 List에 저장
        List<Member> allMember = userRepository.findAll().stream()
                .filter(m -> !m.getId().equals(member.getId()))
                .collect(Collectors.toList());

        //GPT에게 던질 dto에 저장
        GptSimilarUserReq gptSimilarUserReq = new GptSimilarUserReq(
                userId,
                toUserStatusDto(member),
                allMember.stream().map(this::toUserStatusDto).toList()
        );
        // GPT에게 던질 프롬프트 구성
        StringBuilder prompt = new StringBuilder();
        prompt.append("targetUser와 otherUsers는 사용자들의 정보입니다.\n")
                .append("각 사용자는 성별, 나이, 직업, 월소득, 예비자금, 교통수단, 생활패턴 등의 데이터를 포함하고 있습니다.\n")
                .append("targetUser와 가장 유사한 3명의 userId를 JSON 배열로만 반환해주세요.주석, 설명, 줄바꿈도 없이 형식은 무조건 [1, 2, 3] 이런 식의 JSON 배열만 출력하세요.\n 예: [2, 5, 9]\n\n")
                .append("targetUser: ").append(gptSimilarUserReq.getTargetUser()).append("\n")
                .append("otherUsers: ").append(gptSimilarUserReq.getOtherUsers());

        // GPT Chat API 호출을 위한 JSON 요청 구성
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", "gpt-4");

        ArrayNode messages = mapper.createArrayNode();
        ObjectNode userMessage = mapper.createObjectNode();

        userMessage.put("role", "user");
        userMessage.put("content", prompt.toString());

        messages.add(userMessage);
        requestBody.set("messages", messages);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

        // OpenAI Chat API 호출
        String response = restTemplate.postForObject(gptUrl, request, String.class);
        System.out.println(response);
        // 응답에서 유사 userId 추출
        try {
            //JsonNode 객체 리턴
            JsonNode jsonResponse = mapper.readTree(response);
            String content = jsonResponse
                    .path("choices").get(0)
                    .path("message")
                    .path("content").asText();

            //테스트용 로그 코드
            System.out.println("GPT가 반환한 답" + content);

            // 결과 문자열이 "[1,2,3]" 형태라고 가정
            List<Long> similarUserIds = mapper.readValue(content, new TypeReference<List<Long>>() {});

            System.out.println("similarUserIds" + similarUserIds);

            return similarUserIds.stream()
                    .map(SimilarUserReq::new) // SimilarUserReq 생성자에 맞게 수정
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("GPT 응답 파싱 오류", e);
        }
    }

    @Override
    public List<SimilarUserHouseRes> getSimilarUserHouseData(List<SimilarUserReq> similarUsers) {
        //
        System.out.println("similarUsers가 제대로 왔는가" + similarUsers);
        List<SimilarUserHouseRes> similarUserHouseResList = new ArrayList<>();

        for(int i=0;i<similarUsers.size();i++) {
            //Member가 존재하는지 검증
            Member member = userRepository.findById(similarUsers.get(i).getId())
                    .orElseThrow(UserNotFoundException::new);

            userHouseInfoRepository.findByMemberId(member.getId()).stream()
                    .filter(house -> house.getRanking() == 1)
                    .findFirst() // 랭크 1이 여러개라면 첫번째만 가져옴
                    .ifPresent(house -> { //rank == 1이 존재하면
                        SimilarUserHouseRes res = new SimilarUserHouseRes(
                                house.getHouseName(),
                                house.getKind(),
                                house.getSize(),
                                house.getYear_rent(),
                                house.getMonthly_rent(),
                                house.getDeposit(),
                                house.getManagement()
                        );
                        similarUserHouseResList.add(res);
                    });

        }
        return similarUserHouseResList;
    }


    private GptSimilarUserReq.UserStatusDto toUserStatusDto(Member member){
        return new GptSimilarUserReq.UserStatusDto(
                member.getId(),
                member.getSex(),
                member.getAge(),
                member.getJob(),
                member.getMonth_income(),
                member.getReserve_money(),
                member.getTransport(),
                member.getLifestyle_pattern()
        );
    }

}
