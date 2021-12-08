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
@Table(name = "chitietsp")
public class ChiTietSPEntity extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private TaiKhoanEntity sanPhamEntityChiTietSP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mactphieunhap", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private ChiTietPNKEntity chiTietPNKEntityChiTietSP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ct_ddh", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private ChiTietDDHEntity chiTietDDHEntityChiTietSP;


    @Column(name = "spthu")
    private Integer spThu;

    @Column(name = "seri_sp")
    private String seriSP;

    @Column(name = "tinhtrang")
    private String tinhTrang;

}
