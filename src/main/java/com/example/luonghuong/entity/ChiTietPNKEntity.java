package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chitietpnk")
public class ChiTietPNKEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private SanPhamEntity sanPhamEntityChiTietPNK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maphieunhap", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private DonDatHangEntity phieuNhapEntityChiTietPNK;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "gianhap")
    private BigDecimal giaNhap;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @Column(name = "lohang")
    private String loHang;

    @Column(name = "ghichu")
    private Integer ghiChu;

    //    chi tiết phiếu nhập kho_ chi tiết sản phẩm
    @OneToMany(mappedBy = "chiTietPNKEntityChiTietSP")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<ChiTietSPEntity> chiTietPNKEntityChiTietSPs = new ArrayList<>();
}
