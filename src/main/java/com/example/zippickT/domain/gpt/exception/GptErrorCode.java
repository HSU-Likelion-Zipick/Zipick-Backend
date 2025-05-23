package com.example.zippickT.domain.gpt.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GptErrorCode implements BaseResponseCode {
    ;

    private final String code;
    private final int httpStatus;
    private final String message;
}
