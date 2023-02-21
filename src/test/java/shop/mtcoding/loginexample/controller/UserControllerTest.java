package shop.mtcoding.loginexample.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.loginexample.model.User;
import shop.mtcoding.loginexample.util.Encode;

 @Transactional
 @AutoConfigureMockMvc
 @SpringBootTest(webEnvironment = WebEnvironment.MOCK)
 public class UserControllerTest {
 
    @Autowired
    private MockMvc mvc;

    private MockHttpSession mockSession;

    @BeforeEach // Test 메서드 실행 직전에 호출됨
    public void setUp() {
        // 데이터 인서트
        User user = new User("ssar", "1234", "ssar@nate.com");
        user.setId(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now())); // 타임스탬프는 현재 시간을 뽑는 메서드가 없다.

        mockSession = new MockHttpSession();
        mockSession.setAttribute("principal", user);
    }
 
    @Test
    public void join_test() throws Exception {
         // given
         String requestBody = "username=love&password=1234&email=love@nate.com";
 
         // when
         ResultActions resultActions = mvc.perform(post("/join").content(requestBody).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
 
         // then
         resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void login_test() throws Exception {
        // given
        String encPassword = Encode.passwordEncode("1234");
        String requestBody = "username=ssar&password=1234";

        // when
        // mvc.perform 으로 컨트롤러를 때려볼 수 있다. content로 값을 넣으면 타입을 정해주어야 한다. -> content.Type
        ResultActions resultActions = mvc.perform(post("/login").content(requestBody).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        HttpSession session = resultActions.andReturn().getRequest().getSession();
        User principal = (User) session.getAttribute("principal");
        System.out.println(principal.getUsername());

        // then
        assertThat(principal.getId()).isEqualTo(1);
        assertThat(principal.getUsername()).isEqualTo("ssar");
        assertThat(principal.getEmail()).isEqualTo("ssar@nate.com");
        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    public void mainPage_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/"));
        // then
        resultActions.andExpect(status().is3xxRedirection());
    }
}