package com.example.luonghuong.service;

import com.example.luonghuong.dto.request.SanPhamRequest;
import com.example.luonghuong.dto.response.SanPhamDTO;
import com.example.luonghuong.dto.response.SanPhanSuaDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamService {
//    lấy ra sản phẩm cha để trọn trong form add
    List<SanPhamDTO> getProductOne();
//    thêm sản phẩm
    Long themSanPham(SanPhamRequest sanPhamRequest);
//    xoá sản phẩm
    Boolean xoaSanPham(Long id);
//    trả về số trang
    Integer tinhTrang(Long id);
//    phân trang bảng sản phẩm
    List<SanPhamDTO> findAllByPage(Pageable pageable, Long id);

//    tìm sản phẩm theo id
    SanPhanSuaDTO findById(Long id);
}
