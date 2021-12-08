package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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

    //    mapping phieu nhap kho nhan vien
    @OneToMany(mappedBy = "maNVPNK")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<PhieuNhapKhoEntity> phieuNhapKhoNVs = new ArrayList<>();

    //    mapping phieu nhap kho nha cung cap
    @OneToMany(mappedBy = "maNCCPNK")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<PhieuNhapKhoEntity> phieuNhapKhoNCCs = new ArrayList<>();


    //    mapping đơn đặt hàng mã tài khoản 1
    @OneToMany(mappedBy = "maTaiKhoan1DDH")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<PhieuNhapKhoEntity> maTaiKhoan1DDH = new ArrayList<>();

    //    mapping đơn đặt hàng mã tài khoản 2
    @OneToMany(mappedBy = "maTaiKhoan2DDH")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<PhieuNhapKhoEntity> maTaiKhoan2DDH = new ArrayList<>();

    //    tài khoản - đánh giá
    @OneToMany(mappedBy = "taiKhoanEntityDanhGia")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<PhieuNhapKhoEntity> taiKhoanEntityDanhGias = new ArrayList<>();
}
