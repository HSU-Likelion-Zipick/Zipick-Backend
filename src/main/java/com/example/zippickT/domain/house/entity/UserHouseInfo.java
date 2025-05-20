package com.example.zippickT.domain.house.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHouseInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //사용자 집 정보ID

    @NotNull
    private String houseName; //사용자가 입력한 집의 이름

    @NotNull
    private String kind; //사용자가 입력한 집의 종류

    @NotNull
    private int size; //집의 평수

    @NotNull
    private int management; //관리비

    @NotNull
    private boolean parking; //주차여부

    @NotNull
    private boolean elevator; //엘리베이터 여부

    @NotNull
    private String direction; //집의 방향

    @NotNull
    private int completion_date; //준공일

    @NotNull
    private int station; // 역or정류장까지의 도보 거리 소요 시간

    @NotNull
    private int destination; //목적지까지의 소요 시간

    //전세
    private int year_rent; //null 허용

    //월세
    private int monthly_rent; //null 허용

    //보증금
    private int deposit; //null 허용


    @NotNull
    private int full_floors; //전체 층수

    @NotNull
    private int floor; //원하는 층수

    @NotNull
    private int rank; //GPT가 뽑은 순위
}
