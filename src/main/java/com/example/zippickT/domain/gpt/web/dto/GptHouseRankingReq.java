package com.example.zippickT.domain.gpt.web.dto;

import com.example.zippickT.domain.User.entity.*;
import com.example.zippickT.domain.house.entity.UserHouseInfo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
public class GptHouseRankingReq {
    private UserInfoDto userInfo;
    private List<UserHouseInfoDto> userHouseInfos;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoDto{
        private Long userId;

        private Gender sex;

        private int age;

        private Job job;

        private int month_income;

        private int reserve_money;

        private Transport transport;

        private LifeStyle lifestyle_pattern;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserHouseInfoDto{
        private Long house_id;

        private String kind;

        private int size;

        private int management;

        private boolean parking;

        private boolean elevator;

        private String direction;

        private int completion_date;

        private int station;

        private int destination;

        private int year_rent;

        private int monthly_rent;

        private int deposit;

        private int full_floors;

        private int floor;

        private List<String> options;

        private List<String> charges;
    }
}