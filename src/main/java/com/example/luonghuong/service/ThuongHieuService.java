package com.example.luonghuong.service;

import com.example.luonghuong.dto.response.ThuongHieuDTO;
import com.example.luonghuong.entity.ThuongHieuEntity;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieuDTO> findAll();
}
