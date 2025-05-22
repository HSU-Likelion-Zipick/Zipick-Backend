package com.example.zippickT.domain.house.entity;

import com.example.zippickT.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCharge extends BaseEntity {

    //Charge와 UserHouseInfo의 다대다 관계를 풀기위한 중간 매핑 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userHouseInfo_id")
    private UserHouseInfo userHouseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charge_id")
    private Charge charge;

}
