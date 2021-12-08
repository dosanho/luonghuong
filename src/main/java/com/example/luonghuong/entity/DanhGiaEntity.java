package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chitietsp")
public class DanhGiaEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private SanPhamEntity sanPhamEntityDanhGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mataikhoan", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity taiKhoanEntityDanhGia;

    @Column(name = "thoigian")
    private LocalDateTime thoiGian;

    @Column(name = "xephang")
    private String xepHang;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "tthienthi")
    private Integer ttHienThi;

}
