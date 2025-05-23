package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.exception.BaseException;

public class LifeStyleNotFoundException extends BaseException {
    public LifeStyleNotFoundException() {
        super(UserErrorCode.LIFESTYLE_NOT_FOUND_404);
    }
}
