package com.example.zippickT.domain.house.exception;

import com.example.zippickT.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HouseErrorCode implements BaseResponseCode {
    CHARGE_NOT_FOUND_404("CHARGE_NOT_FOUND_404",404, "집의 관리비에 포함되는 요금을 찾을 수 없습니다."),
    OPTION_NOT_FOUND_404("OPTION_NOT_FOUND_404",404, "집의 옵션을 찾을 수 없습니다."),
    HOUSE_NOT_FOUND_404("HOUSE_NOT_FOUND_404", 404,"사용자가 입력한 집의 정보를 찾을 수 없었습니다");

    private final String code;
    private final int httpStatus;
    private final String message;
}
