package com.example.luonghuong.dto.response;

import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Builder
public class SanPhamDTO {

    private Long id;

    private String tenSanPham;


    private String tenThuongHieu;


    private String tenLoai;


    private BigDecimal giaNhap;


    private BigDecimal giaBan;

    private Long maSanPhamCha;

    private String anh;

    private Integer trangThai;
}
