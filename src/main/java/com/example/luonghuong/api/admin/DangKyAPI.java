package com.example.luonghuong.api.admin;

import com.example.luonghuong.dto.request.DangKyRequest;
import com.example.luonghuong.dto.request.SanPhamRequest;
import com.example.luonghuong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class DangKyAPI {
    private final UserService userService;
    @PostMapping
    public Boolean DangKy(@RequestBody @Valid DangKyRequest dangKyRequest, BindingResult bindingResult) throws Exception {
        System.out.println(dangKyRequest);
        //Kiểm tra lỗi có lỗi trả ra 1 exception
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            throw new Exception("request errors");
        }

        return userService.DangKy(dangKyRequest);
    }
}
