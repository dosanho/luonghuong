package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.request.DangKyRequest;
import com.example.luonghuong.entity.TaiKhoanEntity;
import com.example.luonghuong.repository.TaiKhoanRepository;
import com.example.luonghuong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final TaiKhoanRepository taiKhoanRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // lấy ra tài khoản sử dụng tên tài khoản
        TaiKhoanEntity user = this.taiKhoanRepository.findByTenDangNhap(username)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        //  lấy ra loại tải khoản
        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(user.getLoaiTaiKhoan()));

        // tạo Spring security core tài khoản
        User userAuth = new User(user.getTenDangNhap(), user.getMatKhau(), roles);

        // return tại khoản vừa tạo
        return userAuth;
    }



}
