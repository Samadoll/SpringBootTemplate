package com.springboot.springbootTemplate.service;

import com.springboot.springbootTemplate.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     void register(String username, String password);
     UserEntity findUser(String username);
     UserEntity login(String username, String password);
     void updatePassword(int uid, String password);
}
