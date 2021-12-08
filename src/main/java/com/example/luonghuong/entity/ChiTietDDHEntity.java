package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chitietddh")
public class ChiTietDDHEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private SanPhamEntity sanPhamEntityChiTietDDH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madondat", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private DonDatHangEntity donDatHangEntityChiTietDDH;

    @Column(name = "soluong")
    private Integer soLuong;

    @Column(name = "giaBan")
    private BigDecimal giaBan;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @Column(name = "ghichu")
    private Integer ghiChu;

    //    chi tiết đơn đặt hàng_ chi tiết sản phẩm
    @OneToMany(mappedBy = "chiTietDDHEntityChiTietSP")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<ChiTietSPEntity> chiTietDDHEntityChiTietSPs = new ArrayList<>();
}
