package com.example.zippickT.domain.gpt.web.controller;

import com.example.zippickT.domain.gpt.service.GptRankingService;
import com.example.zippickT.domain.gpt.web.dto.GptHouseRankingRes;
import com.example.zippickT.global.response.SuccessResponse;
import com.example.zippickT.domain.gpt.service.GptSimilarService;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt")
public class GptController {
    private final GptRankingService gptRankingService;
    private final GptSimilarService gptSimilarService;

    @PutMapping("/{user_id}/ranking")
    public ResponseEntity<SuccessResponse<?>> houseRanking(@PathVariable Long user_id) {
        System.out.println("ranking API가 호출되었습니다.");
        GptHouseRankingRes res = gptRankingService.recommendByGpt(user_id);
        System.out.println("ranking API가 반환되었습니다.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(res));
    }


    @GetMapping("/{user_id}/similar")
    public ResponseEntity<SuccessResponse<?>> similar(@PathVariable Long user_id) {
        System.out.println("similar API가 호출되었습니다.");
        List<SimilarUserReq> similarUsers = gptSimilarService.getSimilarUser(user_id);

        //Test Log 확인용 코드
        System.out.println("뽑아온 유저의 Id" + similarUsers);

        List<SimilarUserHouseRes> similarUserHouseResList = gptSimilarService.getSimilarUserHouseData(similarUsers);

        //Test Log 확인용 코드
        System.out.println("뽑아온 유저의 집 정보" +similarUserHouseResList);
        System.out.println("similar API가 반환되었습니다.");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(similarUserHouseResList));
    }
}
