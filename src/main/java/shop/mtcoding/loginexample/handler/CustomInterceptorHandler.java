package shop.mtcoding.loginexample.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import shop.mtcoding.loginexample.service.AuthService;

@Component
public class CustomInterceptorHandler implements HandlerInterceptor {

    @Autowired
    private AuthService authService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        String requestUri = request.getRequestURI();

        if (requestUri.equals("/")) {
            HttpSession session = request.getSession();
            
            if (authService.isAuthenticated(session)) {
                return true;
            }
            response.sendRedirect("/loginForm");
            return false;
        }

        return true;
    }
    
}
