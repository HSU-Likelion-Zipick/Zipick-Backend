package com.example.zippickT.domain.gpt.service;

import com.example.zippickT.domain.User.repository.UserRepository;
import com.example.zippickT.domain.gpt.web.dto.SimilarUserHouseRes;
import com.example.zippickT.domain.house.repository.UserHouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GptServiceImpl implements GptService {

    private final UserRepository userRepository;
    private final UserHouseInfoRepository userHouseInfoRepository;
    @Override
    public List<SimilarUserHouseRes> getSimilarUserHouseData(Long userId) {
        return List.of();
    }
}
