package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND_404);
    }
}
