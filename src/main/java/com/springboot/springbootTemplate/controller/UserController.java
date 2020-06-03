package com.springboot.springbootTemplate.controller;

import com.springboot.springbootTemplate.entity.ResponseEntity;
import com.springboot.springbootTemplate.security.IsUser;
import com.springboot.springbootTemplate.service.UserService;
import com.springboot.springbootTemplate.util.Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@IsUser
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @ApiOperation("Update user's password")
    @PutMapping("/password")
    public ResponseEntity updateUser(@RequestParam String password,
                                     @AuthenticationPrincipal UsernamePasswordAuthenticationToken token) {
        userService.updatePassword(Util.getCurrentUid(), password);
        User user = (User) token.getPrincipal();
        // Get rid of old cache
        cacheManager.getCache("jwt-cache").evict(user.getUsername());
        return new ResponseEntity(HttpStatus.OK.value(), "Password Changed", null);
    }
}
