package com.example.zippickT.domain.gpt.service;

import com.example.zippickT.domain.gpt.web.dto.GptHouseRankingRes;
import org.springframework.stereotype.Service;

@Service
public interface GptRankingService {
    GptHouseRankingRes recommendByGpt(Long userId);
}
