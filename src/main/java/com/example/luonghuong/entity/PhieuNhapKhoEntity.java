package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "phieunhapkho")
public class PhieuNhapKhoEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNV", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity maNVPNK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNCC", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity maNCCPNK;

    @Column(name = "trangthaitt")
    private Integer trangThaiTT;

    @Column(name = "thoigian")
    private LocalDateTime thoiGian;

    @Column(name = "tongtien")
    private Integer tongTien;

    @Column(name = "mota")
    private Integer moTa;

    //    san pham _ chi tiết phiếu nhập kho
    @OneToMany(mappedBy = "phieuNhapEntityChiTietPNK")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<ChiTietPNKEntity> phieuNhapEntityChiTietPNKs = new ArrayList<>();



}
