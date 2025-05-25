package com.example.zippickT.domain.gpt.web.dto;

public record SimilarUserHouseRes(
        String houseName, //집 이름
        String kind, //집 종류
        int size, //집 평수
        int year_rent, //전세
        int month_rent, //월세
        int deposit, //보증금
        int management //관리비
) {
}
