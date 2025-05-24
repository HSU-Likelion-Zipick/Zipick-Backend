package com.example.zippickT.domain.gpt.service;

import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GptService {
    List<SimilarUserHouseRes> getSimilarUserHouseData(Long userId);
}
