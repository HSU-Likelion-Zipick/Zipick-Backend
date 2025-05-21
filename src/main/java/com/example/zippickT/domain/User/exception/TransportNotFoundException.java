package com.example.zippickT.domain.User.exception;

import com.example.zippickT.global.exception.BaseException;

public class TransportNotFoundException extends BaseException {
    public TransportNotFoundException() {
        super(UserErrorCode.TRANSPORT_NOT_FOUND_404);
    }
}
