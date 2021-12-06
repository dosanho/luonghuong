package com.example.luonghuong.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
    @GetMapping
    public String homePage(){
       return "/views/user/home";
    }
}
