package shop.mtcoding.loginexample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.loginexample.dto.user.UserReq.JoinReqDto;
import shop.mtcoding.loginexample.handler.ex.CustomException;
import shop.mtcoding.loginexample.model.User;
import shop.mtcoding.loginexample.model.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Transactional
    public void 회원가입(JoinReqDto joinReqDto) {
        User sameuser = userRepository.findByUsername(joinReqDto.getUsername());

        if (sameuser != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }

        try {
            userRepository.insert(new User(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getEmail()));
        } catch (Exception e) {
            throw new CustomException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
