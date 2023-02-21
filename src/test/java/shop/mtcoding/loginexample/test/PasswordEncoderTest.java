package shop.mtcoding.loginexample.test;

import org.junit.jupiter.api.Test;

import shop.mtcoding.loginexample.util.Encode;

public class PasswordEncoderTest {
    
    @Test
    public void passwordEncoder_test() throws Exception {
        // given
        String password = "1234";

        // when
        String encPassword = Encode.passwordEncode(password);

        System.out.println(encPassword);

        boolean isCheck = Encode.matches(password, encPassword);
        System.out.println(isCheck);
    }
}
