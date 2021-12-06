package com.example.luonghuong.repository;

import com.example.luonghuong.entity.SanPhamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPhamEntity,Long> {

    List<SanPhamEntity> findAllByMaSanPhamChaSP(SanPhamEntity sanPhamEntity);

    Page<SanPhamEntity> findAllByMaSanPhamChaSP(Pageable pageable, SanPhamEntity sanPhamEntity);

    Integer countByMaSanPhamChaSP(SanPhamEntity sanPhamEntity);
}
