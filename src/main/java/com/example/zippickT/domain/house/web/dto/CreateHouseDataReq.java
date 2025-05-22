package com.example.zippickT.domain.house.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateHouseDataReq {
    private String houseName; //집 명칭
    private String kind; //집 종류
    private int size; //집 평수
    private int management; //관리비
    private boolean parking; //주차여부
    private boolean elevator; //엘리베이터 여부
    private String direction; //집 방향
    private int completion_date; //준공일
    private int station; //주변 역/정류장까지 걸리는 시간
    private int destination; //주변 목적지까지 걸리는 시간
    private int year_rent; //전세금
    private int monthly_rent; //월세금
    private int deposit; //보증금
    private int full_floors; //전체 층수
    private int floor; //원하는 층수
    private int rank; //추천 순위
    private List<String> selectedCharges; //관리비 선택 항목
    private List<String> selectedOptions; //옵션 선택항목
}
