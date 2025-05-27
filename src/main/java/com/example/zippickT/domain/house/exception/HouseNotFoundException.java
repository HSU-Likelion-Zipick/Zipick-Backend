package com.example.zippickT.domain.house.exception;

import com.example.zippickT.global.exception.BaseException;

public class HouseNotFoundException extends BaseException {
    public HouseNotFoundException() {
        super(HouseErrorCode.HOUSE_NOT_FOUND_404);
    }
}
