package com.example.luonghuong.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dactrung")
public class DacTrungEntity extends BaseEntity{

    @Column(name = "loaidactrung")
    private String loaiDacTrung;

    @Column(name = "thutu")
    private Integer thuTu;


    @Column(name = "ten")
    private String ten;


    @Column(name = "mota")
    private String moTa;

//    san pham _ dac trung
    @OneToMany(mappedBy = "maDacTrung")
    @JsonIdentityReference(alwaysAsId = true) // show only id of Topic
    private List<SanPhamDacTrungEntity> sanPhamDacTrungEntities = new ArrayList<>();
}
