package com.example.luonghuong.repository;

import com.example.luonghuong.entity.ChiTietSPEntity;
import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ChiTietSPRepository extends JpaRepository<ChiTietSPEntity,Long> {
    @Transactional
    void deleteAllBySanPhamEntityChiTietSP(SanPhamEntity sanPhamEntity);
}
