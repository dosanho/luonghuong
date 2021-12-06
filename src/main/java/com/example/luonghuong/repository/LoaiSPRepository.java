package com.example.luonghuong.repository;

import com.example.luonghuong.entity.LoaiSPEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoaiSPRepository extends JpaRepository<LoaiSPEntity,Long> {
    List<LoaiSPEntity> findAllByMaLoaiSPCha(LoaiSPEntity loaiSPEntity);
}
