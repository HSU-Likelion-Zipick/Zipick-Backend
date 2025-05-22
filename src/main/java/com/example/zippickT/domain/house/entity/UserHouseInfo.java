package com.example.zippickT.domain.house.entity;

import com.example.zippickT.domain.User.entity.Member;
import com.example.zippickT.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHouseInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //사용자 집 정보ID

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

    private int ranking; //GPT가 뽑은 순위

    @OneToMany(mappedBy = "userHouseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserCharge> userCharges = new ArrayList<>();
    //cascade = CascadeType.ALL -> 부모 엔티티 저장/수정/삭제 시 자식 엔티티에도 작업 전파
    //orphanRemoval = true -> 부모 엔티티의 컬렉션에서 자식 엔티티가 제거되면 DB에서도 자동 삭제
    @OneToMany(mappedBy = "userHouseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOption> userOptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private Member member;
}
