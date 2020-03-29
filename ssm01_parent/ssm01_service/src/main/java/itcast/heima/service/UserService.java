package itcast.heima.service;

import itcast.heima.domain.User_Attr;
import itcast.heima.domain.User;

import java.util.List;

public interface UserService {

    User UserLogin(User user);
    List<User> findAll();
    void insertUser(User user);
}
