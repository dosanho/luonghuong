package com.example.luonghuong.api.admin;

import com.example.luonghuong.dto.response.LoaiSPDTO;
import com.example.luonghuong.service.LoaiSPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/loaisp")
public class LoaiSPAPI {

    private final LoaiSPService loaiSPService;

    @GetMapping("/{id}")
    public List<LoaiSPDTO> getLoaiSP(@PathVariable(value = "id",required = false) Long id){
        return this.loaiSPService.getLoaiSPByParentId(id);
    }
}
