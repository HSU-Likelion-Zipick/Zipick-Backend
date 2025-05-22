package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseResponseCode {
    TRANSPORT_NOT_FOUND_404("TRANSPORT_NOT_FOUND_404",404,"유효하지 않는 이동수단입니다." ),
    GENDER_NOT_FOUND_404("GENDER_NOT_FOUND_404", 404, "유효하지 않는 성별입니다."),
    JOB_NOT_FOUND_404("JOB_NOT_FOUND_404", 404, "유효하지 않는 직업입니다." ),
    LIFESTYLE_NOT_FOUND_404("LIFESTYLE_NOT_FOUND_404", 404, "유효하지 않는 생활패턴입니다."),
    USER_NOT_FOUND_404("USER_NOT_FOUND_404",404,"유저를 찾을 수 없습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
