package com.example.luonghuong.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SanPhanSuaDTO {
    private Long id;

    private String tenSanPham;

    private Long maThuongHieu;

    private Long maLoaiCha;
    private Long maLoaiCon;

    private BigDecimal giaNhap;


    private BigDecimal giaBan;

    private Long maSanPhamCha;

    private String anh;

    private Integer trangThai;

    private Integer giamGia;
}
