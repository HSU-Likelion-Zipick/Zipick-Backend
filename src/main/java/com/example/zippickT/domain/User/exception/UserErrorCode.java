package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseResponseCode {
    USER_NOT_FOUND_404("USER_NOT_FOUND_404",404,"유저를 찾을 수 없습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
