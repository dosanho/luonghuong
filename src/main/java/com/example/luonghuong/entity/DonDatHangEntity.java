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
@Table(name = "dondathang")
public class DonDatHangEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mataikhoan1", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity maTaiKhoan1DDH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mataikhoan2", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity maTaiKhoan2DDH;

    @Column(name = "trangthaitt")
    private Integer trangThaiTT;

    @Column(name = "thoigian")
    private LocalDateTime thoiGian;

    @Column(name = "tongtien")
    private BigDecimal tongTien;

    @Column(name = "mota")
    private Integer moTa;

    //    mapping đơn đặt hàng với chi tiết đơn đặt hàng
    @OneToMany(mappedBy = "donDatHangEntityChiTietDDH")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<ChiTietDDHEntity> chiTietDDHEntitieCTDDHs = new ArrayList<>();


}
