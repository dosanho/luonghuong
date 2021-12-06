package com.example.luonghuong.controller.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Authentication {
//    gọi trang đăng nhập
    @GetMapping("/login")
    public String getPageLogin(){
        return "views/authentication/login";
    }

//    gọi trang đăng ký
    @GetMapping("/register")
    public String getPageRegister(){
        return "views/authentication/registerUser";
    }

}
