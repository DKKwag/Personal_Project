package com.personal.project.service;

import com.personal.project.dto.LoginRequestDto;
import com.personal.project.dto.SignupRequestDto;
import com.personal.project.model.User;
import com.personal.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    //Bean 의존성을 가짐
    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }





    public void registerUser(SignupRequestDto requestDto) {
        //회원가입한 ID, Password를 userRepository에 저장
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);
    }

    public void loginUser(LoginRequestDto requestDto) {
        //회원가입한 ID, Password를 userRepository에 저장
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
    }
}




