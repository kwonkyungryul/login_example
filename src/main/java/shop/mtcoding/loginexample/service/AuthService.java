package shop.mtcoding.loginexample.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    public boolean isAuthenticated(HttpSession session) {
        // 세션에서 로그인 정보를 가져옵니다.
        String userId = (String) session.getAttribute("userId");
        // 로그인 정보가 있는 경우 true를 반환합니다.
        if (userId != null) {
            return true;
        }
        // 로그인 정보가 없는 경우 false를 반환합니다.
        return false;
    }
}
