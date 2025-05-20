package com.example.zippickT.domain.User.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //사용자 id

    @NotNull
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender sex; //성별

    @NotNull
    private int age; //나이

    @Enumerated(EnumType.STRING)
    @NotNull
    private Job job; //직업

    @NotNull
    private int month_income; //월수입

    @NotNull
    private int reserve_money; //여유자금

    @Enumerated(EnumType.STRING)
    @NotNull
    private LifeStyle lifestyle_pattern; //생활패턴


}
