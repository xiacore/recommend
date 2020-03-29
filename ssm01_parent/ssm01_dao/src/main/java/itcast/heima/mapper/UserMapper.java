package itcast.heima.mapper;

import itcast.heima.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();
    User UserLogin(User user);
    void  insertUser(User user);
}
