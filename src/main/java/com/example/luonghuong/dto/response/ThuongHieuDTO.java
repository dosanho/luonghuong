package com.example.luonghuong.dto.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;


@Data
@Builder
public class ThuongHieuDTO {
    private Long id;

    private String tenThuongHieu;


    private String moTa;

    private String hinhAnh;

    private Integer trangThai;
}
