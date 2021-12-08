package com.example.luonghuong.repository;

import com.example.luonghuong.entity.ChiTietPNKEntity;
import com.example.luonghuong.entity.DanhGiaEntity;
import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface DanhGiaRepository extends JpaRepository<DanhGiaEntity,Long> {
    @Transactional
    void deleteAllBySanPhamEntityDanhGia(SanPhamEntity sanPhamEntity);
}
