package com.personal.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor //클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성
@NoArgsConstructor  // 파라미터가 없는 생성자 생성
public class BoardRequestDto {
    private String name;
    private String contents;
}
