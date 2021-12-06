package com.example.luonghuong.dto.response;


import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class LoaiSPDTO {

    private  Long id;

    private String tenLoaiSP;

    private Long maLoaiSPCha;

    private String moTa;
}
