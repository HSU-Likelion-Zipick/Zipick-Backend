package com.example.zippickT.domain.gpt.web.controller;

import com.example.zippickT.domain.gpt.service.GptService;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import com.example.zippickT.global.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt")
public class GptController {
    private final GptService gptService;

    @GetMapping("/{user_id}/similar")
    public ResponseEntity<SuccessResponse<?>> similar(@PathVariable Long user_id) {
        List<SimilarUserHouseRes> similarUserHouseResList = gptService.getSimilarUserHouseData(user_id);
        return null;
    }
}
