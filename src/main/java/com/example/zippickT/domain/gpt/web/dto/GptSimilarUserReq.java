package com.example.zippickT.domain.gpt.web.dto;

import com.example.zippickT.domain.User.entity.Gender;
import com.example.zippickT.domain.User.entity.Job;
import com.example.zippickT.domain.User.entity.LifeStyle;
import com.example.zippickT.domain.User.entity.Transport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptSimilarUserReq {
    private Long targetUserId;
    private UserStatusDto targetUser;
    private List<UserStatusDto> otherUsers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserStatusDto{
        private Long userId;
        private Gender sex;
        private int age;
        private Job job;
        private int month_income;
        private int reserve_money;
        private Transport transport;
        private LifeStyle lifestyle_pattern;
//
//        private List<String> charges;
//        private List<String> options;
    }
}
