package com.example.zippickT.domain.gpt.exception;

import com.example.zippickT.global.exception.BaseException;

public class NotInsertYearMonthlyMoneyException extends BaseException {
    public NotInsertYearMonthlyMoneyException() {
        super(GptErrorCode.NOT_INSERT_YEAR_MONTHLY_MONEY_403);
    }
}
