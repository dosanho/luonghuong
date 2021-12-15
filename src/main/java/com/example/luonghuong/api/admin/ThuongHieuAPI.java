package com.example.luonghuong.api.admin;

import com.example.luonghuong.dto.response.SanPhamDTO;
import com.example.luonghuong.dto.response.ThuongHieuDTO;
import com.example.luonghuong.service.SanPhamService;
import com.example.luonghuong.service.ThuongHieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api")
public class ThuongHieuAPI {

    private final ThuongHieuService thuongHieuService;

//    tính tổng số trang
    @GetMapping("/brand/total")
    public Integer totalPage(){
        Integer totalPage = thuongHieuService.totalPage();
        return totalPage;
    }
    // phân trang
    //    tính tổng số trang
    @GetMapping("/brand")
    public List<ThuongHieuDTO> getThuongHieuByPage(@RequestParam Integer page){
        Pageable pageable = PageRequest.of(page-1,5);
        return thuongHieuService.getBrandByPage(pageable);
    }

    // thêm sản phẩm method post
    @PostMapping("/brand")
    public Long addBrand(@RequestBody ThuongHieuDTO thuongHieuDTO){
        // trả về id của thương hiệu
        return thuongHieuService.addOrEditBrand(thuongHieuDTO);
    }
    // sửa sản phẩm method put
    @PutMapping("/brand")
    public Long editBrand(@RequestBody ThuongHieuDTO thuongHieuDTO){
        // trả về id của thương hiệu
        return thuongHieuService.addOrEditBrand(thuongHieuDTO);
    }

    // sửa sản phẩm method put
    @DeleteMapping("/brand")
    public Boolean deleteBrand(@RequestBody Long id){
        return thuongHieuService.deleteBrand(id);
    }
}
