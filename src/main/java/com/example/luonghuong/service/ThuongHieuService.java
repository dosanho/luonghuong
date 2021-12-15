package com.example.luonghuong.service;

import com.example.luonghuong.dto.response.ThuongHieuDTO;
import com.example.luonghuong.entity.ThuongHieuEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThuongHieuService {

    List<ThuongHieuDTO> findAll();

    // tính tổng page
    Integer totalPage();

    List<ThuongHieuDTO> getBrandByPage(Pageable pageable);
    //

    // thêm sửa xoá
    //thêm và sửa thương hiệu
    Long addOrEditBrand(ThuongHieuDTO thuongHieuDTO);

    // tìm sản phẩm muốn sửa
    ThuongHieuDTO getOneById(Long id);

    // xoá sản phẩm
    Boolean deleteBrand(Long id);
    //
}
