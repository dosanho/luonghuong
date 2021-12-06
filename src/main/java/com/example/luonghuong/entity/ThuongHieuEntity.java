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
@Table(name = "thuonghieu")
public class ThuongHieuEntity  extends BaseEntity {
    @Column(name = "tenthuonghieu")
    private String tenThuongHieu;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "hinhanh")
    private String hinhAnh;

    //    mapping san pham
    @OneToMany(mappedBy = "maThuongHieuSP")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<SanPhamEntity> sanPhamEntities = new ArrayList<>();
}
