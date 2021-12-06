package com.example.luonghuong.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "taikhoan")
public class TaiKhoanEntity extends BaseEntity{
    @Column(name = "tendangnhap")
    private String tenDangNhap;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "loaitaikhoan")
    private String loaiTaiKhoan;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;
}
