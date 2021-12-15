package com.example.luonghuong.repository;

import com.example.luonghuong.entity.SanPhamEntity;
import com.example.luonghuong.entity.ThuongHieuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPhamEntity,Long> {

    List<SanPhamEntity> findAllByMaSanPhamChaSP(SanPhamEntity sanPhamEntity);

    Page<SanPhamEntity> findAllByMaSanPhamChaSP(Pageable pageable, SanPhamEntity sanPhamEntity);

    Integer countByMaSanPhamChaSP(SanPhamEntity sanPhamEntity);

    @Query("select s.id from SanPhamEntity s where s.maThuongHieuSP.id = :brandId")
    List<Long> getIdByMaThuongHieu(Long brandId);
}
