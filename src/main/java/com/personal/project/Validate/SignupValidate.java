package com.personal.project.Validate;
import com.personal.project.dto.LoginRequestDto;
import com.personal.project.dto.SignupRequestDto;
import com.personal.project.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class SignupValidate {

    private final UserRepository userRepository;

    //생성자 Bean의존성을 가짐
    public SignupValidate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //userRepository에 있는 existsByUsername이라는 변수를 가져와 username의 값을 넣어놓음
    public boolean checkUsernameDuplicate(String username){
        return userRepository.existsByUsername(username);
    }
    public boolean checkPassword(String password){
        return userRepository.existsByPassword(password);
    }


    //회원가입시, 유효성 체크
    public Map<String, String> validHandling(Errors errors) {
        //Map안에 error code라는 키값과 error default message라는 value값을 넣음
        Map<String, String> validResult = new HashMap<>();   // 타입 파라미터 생략가능 하게 HashMap<>괄호안의 내용을 비움
        //errors.getFieldErrors()는 오류가 있는경우 필드와 관련된 첫번째 오류만 가져옴
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = "message";
            //key name을 message로 , value를 error의 defaultMessage로 List형태로 저장
            validResult.put(validKeyName, error.getDefaultMessage());
        }
        return validResult;
    }


    //유효성 검사후 error시 return시킬 메세지 작성 후 return
    public String getValidMessage(SignupRequestDto requestDto, Errors errors) {
        //error를 가지고 있을 경우
        if (errors.hasErrors()) {
            //Map은 key값과 value값으로 데이터 핸들
            //return 된 validResult의 값을 가져와서 List형태로 저장(key와 value를 가지는)
            Map<String, String> validResult = validHandling(errors);
            //key값("message")에 있는 default메세지를 return
            return validResult.get("message");
        } else if (checkUsernameDuplicate(requestDto.getUsername())) {
            return "중복된 아이디입니다.";
        } else if (requestDto.getPassword().contains(requestDto.getUsername())){
            return "패스워드에 아이디가 포함될수 없습니다.";
        } else if (!Objects.equals(requestDto.getPassword(), requestDto.getPasswordCheck()))
            return "패스워드와 패스워드 확인을 정확하게 작성해주세요.";
        //error가 없을 시 회원가입 성공이라는 메세지 return
        return "회원가입 성공";
    }

    public String getValidMessage1(LoginRequestDto requestDto, Errors errors) {
        //error를 가지고 있을 경우
        if (errors.hasErrors()) {
            //Map은 key값과 value값으로 데이터 핸들
            //return 된 validResult의 값을 가져와서 List형태로 저장(key와 value를 가지는)
            Map<String, String> validResult = validHandling(errors);
            //key값("message")에 있는 default메세지를 return
            return validResult.get("message");
        } else if(!checkUsernameDuplicate(requestDto.getUsername())){
            return requestDto.getUsername() + "는 없는 아이디 입니다.";
        } else if(!checkPassword(requestDto.getPassword())){
            return "저장된 패스워드와 맞지 않습니다.";
        }
        //error가 없을 시 회원가입 성공이라는 메세지 return
        return "로그인 성공";
    }
}
