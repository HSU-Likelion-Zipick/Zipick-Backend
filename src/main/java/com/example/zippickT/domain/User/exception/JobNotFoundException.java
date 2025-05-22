package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.exception.BaseException;

public class JobNotFoundException extends BaseException {
    public JobNotFoundException() {
        super(UserErrorCode.JOB_NOT_FOUND_404);
    }
}
