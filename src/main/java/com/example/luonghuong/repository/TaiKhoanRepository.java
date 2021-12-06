package com.example.luonghuong.repository;

import com.example.luonghuong.entity.TaiKhoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoanEntity,Long> {
//    tìm tên đăng nhập
    Optional<TaiKhoanEntity> findByTenDangNhap(String tenDangNhap);

// kiểm tra xem tên đăng nhâp hoặc mật khẩu tổn tại hay chưa
    Boolean existsByTenDangNhapOrEmail(String tenDangNhap,String Email);

}
