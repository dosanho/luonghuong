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
@Table(name = "sanpham")
public class SanPhamEntity extends BaseEntity {

    @Column(name = "tensanpham")
    private String tenSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maloaisanpham", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private LoaiSPEntity maLoaiSanPhamSP;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mathuonghieu", nullable = false)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private ThuongHieuEntity maThuongHieuSP;

    @Column(name = "gianhap")
    private BigDecimal giaNhap;

    @Column(name = "giaban")
    private BigDecimal giaBan;

//    san pham cha
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "masanphamcha", nullable = true)
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private SanPhamEntity maSanPhamChaSP;
///

//

    @Column(name = "anh")
    private String anh;

    @Column(name = "trangthai")
    private Integer trangThai;

    @Column(name = "giamgia")
    private Integer giamGia;

    //    đại quy
    @OneToMany(mappedBy = "maSanPhamChaSP")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<SanPhamEntity> sanPhamConEntities = new ArrayList<>();

    //    san pham _ dac trung
    @OneToMany(mappedBy = "maSanPham")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<SanPhamDacTrungEntity> sanPhamDacTrungEntities = new ArrayList<>();


}
