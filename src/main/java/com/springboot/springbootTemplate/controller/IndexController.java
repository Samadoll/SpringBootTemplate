package com.springboot.springbootTemplate.controller;

import com.springboot.springbootTemplate.entity.ResponseEntity;
import com.springboot.springbootTemplate.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "User Registration", notes = "Send token after successful login, require token one every request")
    @PostMapping("/register")
    public ResponseEntity register(@RequestParam @Length(min = 1, max = 100) String username,
                                   @RequestParam @Length(min = 1, max = 100) String password) {
        userService.register(username, password);
        return new ResponseEntity(HttpStatus.OK.value(), "Successfully Registered", null);
    }
}
