package com.example.zippickT.domain.house.exception;

import com.example.zippickT.global.exception.BaseException;

public class ChargeNotFoundException extends BaseException {
    public ChargeNotFoundException() {
        super(HouseErrorCode.CHARGE_NOT_FOUND_404);
    }
}
