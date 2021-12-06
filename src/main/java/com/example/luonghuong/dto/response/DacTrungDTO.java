package com.example.luonghuong.dto.response;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class DacTrungDTO {

    private Long id;
    private String loaiDacTrung;

    private Integer thuTu;

    private String ten;

    private String moTa;
}
