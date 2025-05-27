package com.example.zippickT.domain.gpt.exception;

import com.example.zippickT.global.exception.BaseException;

public class RecommendationHouseNotFoundException extends BaseException {
    public RecommendationHouseNotFoundException() {
        super(GptErrorCode.RECOMMENDATION_HOUSE_NOT_FOUND_404);
    }
}
