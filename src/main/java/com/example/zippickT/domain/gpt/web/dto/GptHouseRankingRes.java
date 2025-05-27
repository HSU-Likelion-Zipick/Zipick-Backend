package com.example.zippickT.domain.gpt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record GptHouseRankingRes(
        Long userId,
        String nickname,
        List<HouseInfoSummeryDto> recommend
) {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class HouseInfoSummeryDto {
        private int rank;
        private Long houseId;
        private String houseName;
        private String kind;
        private int size;
        private String rent; //전세 또는 월세의 비용
        private int management;

    }
}
