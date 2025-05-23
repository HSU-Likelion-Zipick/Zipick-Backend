package com.example.zippickT.domain.gpt.web.controller;

import com.example.zippickT.domain.gpt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gpt")
public class GptController {
    private final GptService gptService;
}
