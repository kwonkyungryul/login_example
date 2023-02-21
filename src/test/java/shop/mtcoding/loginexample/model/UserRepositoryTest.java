package shop.mtcoding.loginexample.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.loginexample.dto.user.UserReq.LoginReqDto;

@Transactional
@MybatisTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAll_test() throws Exception {
        // given

        // when
        List<User> userList = userRepository.findAll();

        // then
        assertThat(userList.get(0).getUsername()).isEqualTo("ssar");
        assertThat(userList.get(0).getPassword()).isEqualTo("1234");
        assertThat(userList.get(0).getEmail()).isEqualTo("ssar@nate.com");
        assertThat(userList.get(1).getUsername()).isEqualTo("cos");
        assertThat(userList.get(1).getPassword()).isEqualTo("1234");
        assertThat(userList.get(1).getEmail()).isEqualTo("cos@nate.com");
    }

    @Test
    public void findById_test() throws Exception {
        // given
        int id = 1;
        
        // when
        User user = userRepository.findById(id);
        
        // then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getEmail()).isEqualTo("ssar@nate.com");
    }

    @Test
    public void findByUsername_test() throws Exception {
        // given
        String username = "ssar";
        
        // when
        User user = userRepository.findByUsername(username);
        
        // then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getEmail()).isEqualTo("ssar@nate.com");
    }

    @Test
    public void findByUsernameAndPassword_test() throws Exception {
        // given
        String username = "ssar";
        String password = "1234";
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername(username);
        loginReqDto.setPassword(password);
        
        // when
        User user = userRepository.findByUsernameAndPassword(loginReqDto);
        
        // then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getPassword()).isEqualTo("1234");
        assertThat(user.getEmail()).isEqualTo("ssar@nate.com");
    }

    @Test
    public void insert_test() throws Exception {
        // given
        String username = "love";
        String password = "1234";
        String email = "love@nate.com";
        User user = new User(username, password, email);
        
        // when
        int result = userRepository.insert(user);
        
        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void update_test() throws Exception {
        // given
        Integer id = 1;
        String username = "ssar";
        String password = "3456";
        String email = "ssar@nate.com";
        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        User user = new User(username, password, email);
        user.setId(id);
        user.setCreatedAt(createdAt);
        
        // when
        int result = userRepository.updateById(user);
        
        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void deleteById_test() throws Exception {
        // given
        int id = 1;

        // when
        int result = userRepository.deleteById(id);

        //then
        assertThat(result).isEqualTo(1);
    }

}
