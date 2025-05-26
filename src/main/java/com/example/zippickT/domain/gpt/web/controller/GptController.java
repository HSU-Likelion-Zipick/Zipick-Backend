package com.example.zippickT.domain.gpt.web.controller;

import com.example.zippickT.domain.gpt.service.GptRankingService;
import com.example.zippickT.domain.gpt.service.GptRankingServiceImpl;
import com.example.zippickT.domain.gpt.web.dto.GptHouseRankingRes;
import com.example.zippickT.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt")
public class GptController {
    private final GptRankingService gptRankingService;

    @GetMapping("/{user_id}/ranking")
    public ResponseEntity<SuccessResponse<?>> houseRanking(@PathVariable Long userId) {
        GptHouseRankingRes res = gptRankingService.recommendByGpt(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(res));
    }
}
