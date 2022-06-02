package com.personal.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter

// RequestDto를 활용해 클라이언트에서 받은 내용을 가지고다님
public class SignupRequestDto {

    @NotNull
    @Pattern(regexp="(?=.*[0-9])|(?=.*[a-zA-Z]).{3,}", message = "이름은 3자 이상, 영문 대,소문자 혹은 숫자만 가능")
    private String username;

    @NotNull
    @Size(min = 4, message = "비밀번호는 4자 이상.")
    private String password;

    @NotNull
    private String passwordCheck;
}
