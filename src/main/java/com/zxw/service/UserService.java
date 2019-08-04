package com.zxw.service;

import com.zxw.auth.entity.UserInfo;
import com.zxw.auth.utils.JwtUtils;
import com.zxw.config.JwtProperties;
import com.zxw.mapper.UserMapper;
import com.zxw.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String login(String username, String password, JwtProperties properties) {

        User user =  userMapper.login(username, password);
        UserInfo info = new UserInfo();
        info.setId(Long.valueOf(user.getId()));
        info.setUsername(user.getUsername());
        info.setQx(String.valueOf(user.getStatus()));
        try {
            String token = JwtUtils.generateToken(info, properties.getPrivateKey(), properties.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(User user) {
        userMapper.save(user);
    }


    public User findById(Long id) {
        User user = userMapper.findById(String.valueOf(id));
        return user;
    }

    public User findById(String id){
        User user = userMapper.findById(id);
        return user;
    }

    public List<User> findAll() {
        List<User> list = userMapper.findAll();
        return list;
    }

    public void update(User id) {
        User user = userMapper.findById(id.getId());
        user.setVersion(id.getVersion());
        userMapper.update(user);
    }
}
