package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "loaisp")
public class LoaiSPEntity extends BaseEntity{

    @Column(name = "tenloaisp")
    private String tenLoaiSP;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloaispcha", nullable = true)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private LoaiSPEntity maLoaiSPCha;

    @Column(name = "mota")
    private String moTa;

//    đại quy
    @OneToMany(mappedBy = "maLoaiSPCha")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<LoaiSPEntity> loaiSPEntityList = new ArrayList<>();

    //    mapping san pham
    @OneToMany(mappedBy = "maLoaiSanPhamSP")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<SanPhamEntity> sanPhamEntities = new ArrayList<>();
}
