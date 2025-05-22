package com.example.zippickT.domain.house.exception;

import com.example.zippickT.global.exception.BaseException;

public class OptionNotFoundException extends BaseException {
    public OptionNotFoundException() {
        super(HouseErrorCode.OPTION_NOT_FOUND_404);
    }
}
