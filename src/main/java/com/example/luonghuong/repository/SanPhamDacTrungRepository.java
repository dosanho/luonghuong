package com.example.luonghuong.repository;

import com.example.luonghuong.entity.SanPhamDacTrungEntity;
import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SanPhamDacTrungRepository extends JpaRepository<SanPhamDacTrungEntity,Long> {

    @Query("select s.maDacTrung.id from SanPhamDacTrungEntity s where s.maSanPham =:sanPhamEntity")
    List<Long> getDacTrungSanPhm(SanPhamEntity sanPhamEntity);

    @Transactional
    void deleteAllByMaSanPham(SanPhamEntity sanPhamEntity);
}
