package com.example.zippickT.domain.gpt.web.dto;

public record SimilarUserHouseRes(
        String houseName, //집 이름
        String kind, //집 종류
        int size, //집 평수
        int cost, //전세 or 월세
        int management //관리비
) {
}
