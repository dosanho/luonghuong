package com.example.luonghuong.repository;

import com.example.luonghuong.entity.DacTrungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;

public interface DacTrungRepository extends JpaRepository<DacTrungEntity,Long> {

    @Query("select s.loaiDacTrung from DacTrungEntity s")
    HashSet<String> getLoaiDacTrungHQL();

    List<DacTrungEntity> findAllByLoaiDacTrung(String loaiDacTrung);
}
