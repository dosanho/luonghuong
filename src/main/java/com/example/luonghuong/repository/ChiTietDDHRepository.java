package com.example.luonghuong.repository;

import com.example.luonghuong.entity.ChiTietDDHEntity;

import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ChiTietDDHRepository extends JpaRepository<ChiTietDDHEntity,Long> {

    @Transactional
    void deleteAllBySanPhamEntityChiTietDDH(SanPhamEntity sanPhamEntity);

}
