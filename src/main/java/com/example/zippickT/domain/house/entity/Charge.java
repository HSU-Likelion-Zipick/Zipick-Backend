package com.example.zippickT.domain.house.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String charge_name; //예시 water, heating 등등 관리비내역 이름임
    // id : 1     water 이렇게 고정으로 넣어두고 활용할 예정임
    // id : 2     heating
    // id : 3     management

    //여기는 고정항목이라 굳이 OneToMany를 안해도 될 듯 함.

}
