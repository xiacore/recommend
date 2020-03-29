package itcast.heima.service.impl;

import itcast.heima.domain.User_Attr;
import itcast.heima.domain.User;

import itcast.heima.mapper.UserMapper;
import itcast.heima.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public User UserLogin(User user) {
        System.out.println("impl");
        User u  = userMapper.UserLogin(user);
        return userMapper.UserLogin(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
