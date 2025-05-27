package com.example.zippickT.domain.gpt.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptSimilarUserRes {
    private List<Long> similarUserIds;
}
