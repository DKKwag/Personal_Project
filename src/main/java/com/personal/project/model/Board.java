package com.personal.project.model;

import com.personal.project.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임하는 방법 (데이터베이스에 의존적)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contents;

    public Board(BoardRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.name = requestDto.getName();
    }
}
