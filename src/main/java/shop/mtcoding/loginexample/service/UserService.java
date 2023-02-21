package shop.mtcoding.loginexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.loginexample.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.loginexample.dto.user.UserReq.LoginReqDto;
import shop.mtcoding.loginexample.handler.ex.CustomException;
import shop.mtcoding.loginexample.model.User;
import shop.mtcoding.loginexample.model.UserRepository;
import shop.mtcoding.loginexample.util.Encode;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        User sameuser = userRepository.findByUsername(joinReqDto.getUsername());

        if (sameuser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }
        String encodedPassword = "";
        try {
            encodedPassword = Encode.passwordEncode(joinReqDto.getPassword());

        } catch (Exception e ) {
            throw new CustomException("비밀번호 해싱 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        joinReqDto.setPassword(encodedPassword);

        try {
            userRepository.insert(new User(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getEmail()));
        } catch (Exception e) {
            throw new CustomException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public User 로그인(LoginReqDto loginReqDto) {
        User userPS = userRepository.findByUsername(loginReqDto.getUsername());

        boolean isCheck = false;

        try {
            isCheck = Encode.matches(loginReqDto.getPassword(), userPS.getPassword());
        } catch (Exception e) {
            throw new CustomException("?");
        }

        if (!isCheck) {
            throw new CustomException("비밀번호가 일치하지 않습니다.");
        }

        loginReqDto.setPassword(userPS.getPassword());

        // System.out.println(isCheck);
        
        User principal = userRepository.findByUsernameAndPassword(loginReqDto);
        if (principal == null) {
            throw new CustomException("일치하는 회원정보가 없습니다.");
        }
        

        return principal;
    }
}