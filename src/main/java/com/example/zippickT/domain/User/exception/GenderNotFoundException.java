package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.exception.BaseException;

public class GenderNotFoundException extends BaseException {
    public GenderNotFoundException() {
        super(UserErrorCode.GENDER_NOT_FOUND_404);
    }
}
