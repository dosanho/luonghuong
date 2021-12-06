package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "dactrung_sanpham")
public class SanPhamDacTrungEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private SanPhamEntity maSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "madactrung", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private DacTrungEntity maDacTrung;

    @Column(name = "mota")
    private String moTa;
}
