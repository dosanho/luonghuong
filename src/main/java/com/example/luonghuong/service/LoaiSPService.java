package com.example.luonghuong.service;

import com.example.luonghuong.dto.response.LoaiSPDTO;
import com.example.luonghuong.entity.LoaiSPEntity;

import java.util.List;

public interface LoaiSPService {
    List<LoaiSPDTO> getLoaiSPByParentId(Long id);
}
