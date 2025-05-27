package com.example.zippickT.domain.gpt.service;

import com.example.zippickT.domain.User.entity.Member;
import com.example.zippickT.domain.User.exception.UserNotFoundException;
import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.gpt.exception.NotInsertYearMonthlyMoneyException;
import com.example.zippickT.domain.gpt.exception.TranslationHouseNotFoundException;
import com.example.zippickT.domain.gpt.web.dto.GptHouseRankingRes;
import com.example.zippickT.domain.house.entity.UserHouseInfo;
import com.example.zippickT.domain.house.exception.HouseNotFoundException;
import com.example.zippickT.domain.house.repository.UserHouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GptRankingServiceImpl implements GptRankingService {

    private final UserRepository userRepository;
    private final UserHouseInfoRepository userHouseInfoRepository;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;


    @Override
    public GptHouseRankingRes recommendByGpt(Long userId) {

        //사용자 정보 조회
        Member user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        //사용자가 작성한 집 정보를 houses 리스트에 저장
        List<UserHouseInfo> houses = userHouseInfoRepository.findByMemberId(userId);
        if (houses.isEmpty()) {
            throw new HouseNotFoundException();
        }

        //Gpt 프롬프트 생성
        String prompt = buildPrompt(user, houses);

        System.out.println("callGpt() 시작, 프롬프트 길이: " + prompt.length());
        String gptResponse = callGpt(prompt);
        System.out.println("GPT 응답 받은 내용: " + gptResponse);

        //gpt 응답에서 순위 저장 Map<집Id, 순위>
        List<Map.Entry<Long,Integer>> ranking = GptResRanking(gptResponse);

        //프론트 응답 dto 생성
        List<GptHouseRankingRes.HouseInfoSummeryDto> recommend = new ArrayList<>();

        for(Map.Entry<Long, Integer> entry : ranking){
            Long houseId = entry.getKey(); //집 id
            Integer rank = entry.getValue(); //해당 집이 랭킹

            UserHouseInfo house = houses.stream()
                    .filter(h-> h.getId().equals(houseId))
                    .findFirst()
                    .orElseThrow(TranslationHouseNotFoundException::new);

            String rent = checkRent(house);

            recommend.add(new GptHouseRankingRes.HouseInfoSummeryDto(
                    rank, //랭킹
                    house.getId(), //집 아이디
                    house.getHouseName(), //집 스펙 닉넴
                    house.getKind(), //집 종류
                    house.getSize(), //평수
                    rent, //전,월세
                    house.getManagement() //관리비
            ));
            //DB에 ranking 저장
            house.setRanking(rank);
            userHouseInfoRepository.save(house);
        }
        return new GptHouseRankingRes(user.getId(), user.getNickname(), recommend);
    }

    //해당 집 정보의 전세, 월세 파악
    private String checkRent(UserHouseInfo house) {
        if(house.getYear_rent() > 0){
            return "전세 " + house.getYear_rent() + "만원";
        }
        else if(house.getMonthly_rent() > 0){
            return "월세 " + house.getMonthly_rent() + " / 보증금 " + house.getDeposit();
        }
        else{
            throw new NotInsertYearMonthlyMoneyException();
        }
    }

    //gpt 응답에서 순위 뽑아내기
    //Map.entry는 하나의 키, 값을 쌍으로 나타냄
    private List<Map.Entry<Long, Integer>> GptResRanking(String content) {
        //숫자 + "위: " + 숫자 형태를 찾음
        Pattern pattern = Pattern.compile("(\\d+)위:\\s*집 스펙의 id (\\d+)");
        Matcher matcher = pattern.matcher(content);
        List<Map.Entry<Long, Integer>> result = new ArrayList<>();

        while( matcher.find()) {
            int rank = Integer.parseInt(matcher.group(1)); //첫번째 숫자(순위)
            Long id = Long.parseLong(matcher.group(2)); //두번째 숫자(집 ID)
            result.add(Map.entry(id, rank));
        }
        return result;
    }

    //프롬프트 작성
    private String buildPrompt(Member m, List<UserHouseInfo> houses) {
        //사용자 정보 문자열
        String userInfo = String.format("""
                [사용자 정보]
                성별: %s
                나이: %d
                직업: %s
                월수입: %d만원
                여유자금: %d만원
                교통수단: %s
                생활패턴: %s
        
                """,
                m.getSex(),
                m.getAge(),
                m.getJob(),
                m.getMonth_income(),
                m.getReserve_money(),
                m.getTransport(),
                m.getLifestyle_pattern()
        );

        //집 목록 문자열
        String houseList = houses.stream()
                .map(h->String.format("- (id: %d) %s, %d평, %s향, %s, %s, 역까지 %d분, 목적지까지 %d분%s%s%s",
                        h.getId(),
                        h.getKind(),
                        h.getSize(),
                        h.getDirection(),
                        h.isParking() ? "주차 가능" : "주차 불가",
                        h.isElevator() ? "엘리베이터 있음" : "엘리베이터 없음",
                        h.getStation(),
                        h.getDestination(),
                        h.getYear_rent() > 0 ? ", 전세 "+h.getYear_rent() + "만원" : "",
                        h.getMonthly_rent() > 0 ? ", 월세 " + h.getMonthly_rent() + " / 보증금 " + h.getDeposit() : "",
                        (!h.getUserOptions().isEmpty()) ? ", 옵션: " + h.getUserOptions().stream()
                                .map(userOption -> userOption.getHouseOption().getOptionName())
                                .collect(Collectors.joining(", "))
                                : "",
                        (!h.getUserCharges().isEmpty()) ?", 관리비에 포함되는 요금: "+h.getUserCharges().stream()
                                .map(userCharge -> userCharge.getCharge().getChargeName())
                                .collect(Collectors.joining(", "))
                                : ""
                ))
                .collect(Collectors.joining("\n"));

        int recommendCount = houses.size();

        //최종 프롬프트
        String prompt = String.format("""
        %s
        [사용자가 작성한 집 스펙 목록]
        %s

        [요청]
        사용자가 작성한 %d개의 집 스펙 중에 사용자 정보를 기준으로 사용자에게 가장 적합한 집 스펙의 순위를 추천해주세요.
        이유나 설명은 포함하지 않습니다.
        형식:
        %s
        """, userInfo, houseList, recommendCount,
                Ranking(recommendCount)
        );

        return prompt;
    }

    private String Ranking(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            sb.append(i).append("위: 집 스펙의 id\n");
        }
        return sb.toString().trim();
    }

    //gpt 요청
    private String callGpt(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "model", "gpt-4o",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        //첫번째 생성된 응답 메시지 내용 출력
        Map<String, Object> choice = ((List<Map<String, Object>>) response.getBody().get("choices")).get(0);
        Map<String, String> message = (Map<String, String>) choice.get("message");

        return message.get("content");
    }

}
