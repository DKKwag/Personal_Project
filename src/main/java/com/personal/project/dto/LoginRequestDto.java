package com.personal.project.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
public class LoginRequestDto {

    @NotNull
    @Pattern(regexp="(?=.*[0-9])|(?=.*[a-zA-Z]).{3,}", message = "이름은 3자 이상, 영문 대,소문자 혹은 숫자만 가능")
    private String username;

    @NotNull
    @Size(min = 4, message = "비밀번호는 4자 이상.")
    private String password;
}
