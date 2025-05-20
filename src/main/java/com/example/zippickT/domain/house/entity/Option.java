package com.example.zippickT.domain.house.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_name", nullable = false)
    private String optionName;

    //id : 1      세탁기
    //id : 2      인덕션
    //id : 3      냉장고
    //이렇게 고정된 값을 넣어주고 사용할 예정
}
