package com.example.zippickT.domain.gpt.service;

import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GptService {
    List<SimilarUserReq> getSimilarUser(Long userId);

    List<SimilarUserHouseRes> getSimilarUserHouseData();


}
