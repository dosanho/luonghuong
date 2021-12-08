package com.example.luonghuong.repository;

import com.example.luonghuong.entity.ChiTietPNKEntity;
import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ChiTietPNKRepository extends JpaRepository<ChiTietPNKEntity,Long> {

    @Transactional
    void deleteAllBySanPhamEntityChiTietPNK(SanPhamEntity sanPhamEntity);
}
