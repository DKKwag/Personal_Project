package com.personal.project.controller;
import com.personal.project.Validate.SignupValidate;
import com.personal.project.dto.LoginRequestDto;
import com.personal.project.dto.SignupRequestDto;
import com.personal.project.security.UserDetailsImpl;
import com.personal.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final SignupValidate signupValidate;

    //Bean의존성을 가짐
    @Autowired
    public UserController(UserService userService, SignupValidate signupValidate){
        this.userService = userService;
        this.signupValidate = signupValidate;
    }
    @GetMapping("/signup")
    public String registerpage(){
        return "signup";
    }


    //회원가입 post
    @PostMapping("/user/signup")
    public String registerUser(@Valid SignupRequestDto requestDto, Errors errors, Model model){ // 알아둬야 되는건 Model을 매개변수로 불러옴으로써 어노테이션 @ModelAttribute도 함께 가져옴
        //Valid패키지 안에있는 메세지를 불러오는 메소드인 getValidateMessage 를 가져와서 message변수에 넣어줌
        String message = signupValidate.getValidMessage(requestDto, errors);
        //만약 message 가 "회원가입 성공"을 가리킬시 Userservice에서 DB에 저장
        if(message.equals("회원가입 성공")){
            userService.registerUser(requestDto);
            return "login";
        }
        // model 객체는 Controller에서 생성된 데이터를 담아 View로 전달할 때 사용되는 객체
        // message가 "회원가입 성공"을 가리키지 않을 시 model 매개변수를 가져와 "message"라는 key값으로 message라는 value값을 넣어줌
        model.addAttribute("message1",message);
        return "signup";
    }

//    //로그인 post
//    @GetMapping ("/user/login")
//    public String loginUser(LoginRequestDto requestDto, Errors errors, Model model){ // 알아둬야 되는건 Model을 매개변수로 불러옴으로써 어노테이션 @ModelAttribute도 함께 가져옴
//        //Valid패키지 안에있는 메세지를 불러오는 메소드인 getValidateMessage 를 가져와서 message변수에 넣어줌
//        String message = signupValidate.getValidMessage1(requestDto, errors);
//        //만약 message 가 "회원가입 성공"을 가리킬시 Userservice에서 DB에 저장
//        if(message.equals("로그인 성공")){
//            userService.loginUser(requestDto);
//            return "index";
//        }
//        // model 객체는 Controller에서 생성된 데이터를 담아 View로 전달할 때 사용되는 객체
//        // message가 "회원가입 성공"을 가리키지 않을 시 model 매개변수를 가져와 "message"라는 key값으로 message라는 value값을 넣어줌
//        model.addAttribute("message",message);
//        return "login";
//    }

}


