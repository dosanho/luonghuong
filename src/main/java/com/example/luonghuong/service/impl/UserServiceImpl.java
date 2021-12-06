package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.request.DangKyRequest;
import com.example.luonghuong.entity.TaiKhoanEntity;
import com.example.luonghuong.repository.TaiKhoanRepository;
import com.example.luonghuong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Boolean DangKy(DangKyRequest dangKyRequest) {
        // kiếm tra tên đăng nhập hoặc email đã tồn tại chưa
        Boolean checkUserName = this.taiKhoanRepository
                .existsByTenDangNhapOrEmail(dangKyRequest.getTenDangNhap(),dangKyRequest.getEmail());
        if (checkUserName) return false;
        else {
            // tạo 1 entity (đại điện cho tài khoản vừa truyền vào)
            TaiKhoanEntity taiKhoanEntity = new TaiKhoanEntity();
            taiKhoanEntity.setTenDangNhap(dangKyRequest.getTenDangNhap());
            // mã hoá mật khẩu theo thuật toán MD5
            taiKhoanEntity.setMatKhau(bCryptPasswordEncoder.encode(dangKyRequest.getMatKhau()));

            taiKhoanEntity.setHoTen(dangKyRequest.getHoTen());

            taiKhoanEntity.setEmail(dangKyRequest.getEmail());
            taiKhoanEntity.setSdt(dangKyRequest.getSdt());
            taiKhoanEntity.setLoaiTaiKhoan("USER");
            this.taiKhoanRepository.save(taiKhoanEntity);
            return true;
        }

    }
}
