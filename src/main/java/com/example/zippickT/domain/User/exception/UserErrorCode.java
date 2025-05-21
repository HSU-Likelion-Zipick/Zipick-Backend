package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseResponseCode {
    TRANSPORT_NOT_FOUND_404("TRANSPORT_NOT_FOUND_404",404,"해당 이동수단이 존재하지 않습니다." );

    private final String code;
    private final int httpStatus;
    private final String message;
}
