package shop.mtcoding.loginexample.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import shop.mtcoding.loginexample.dto.user.UserReq.LoginReqDto;

@Mapper
public interface UserRepository {
    public List<User> findAll();

    public User findById(int id);

    public User findByUsername(String username);
    
    public User findByUsernameAndPassword(LoginReqDto loginReqDto);
    
    public int insert(User user);

    public int updateById(User user);

    public int deleteById(int id);

}