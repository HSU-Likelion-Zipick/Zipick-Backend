package com.example.zippickT.domain.gpt.exception;

import com.example.zippickT.global.exception.BaseException;

public class TranslationHouseNotFoundException extends BaseException {
    public TranslationHouseNotFoundException() {
        super(GptErrorCode.TRANSLATION_HOUSE_NOT_FOUND_404);
    }
}
