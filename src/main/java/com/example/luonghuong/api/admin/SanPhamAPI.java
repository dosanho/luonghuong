package com.example.luonghuong.api.admin;

import com.example.luonghuong.dto.request.SanPhamRequest;
import com.example.luonghuong.dto.response.SanPhamDTO;
import com.example.luonghuong.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api")
public class SanPhamAPI {

    private final SanPhamService sanPhamService;

    @PostMapping("/sanpham")
    public Long themSanPham(@RequestBody @Valid SanPhamRequest sanPhamRequest, BindingResult bindingResult) throws Exception {
        //Kiểm tra lỗi có lỗi trả ra 1 exception
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            throw new Exception("request errors");
        }

        return sanPhamService.themSanPham(sanPhamRequest);
    }

    @GetMapping({"/sanpham/trang","/sanpham/trang/{id}"})
    public Integer getPageProduct(@PathVariable(value = "id",required = false) Long id){
        return this.sanPhamService.tinhTrang(id);
    }
    @GetMapping({"/sanpham/show","/sanpham/show/{id}"})
    public List<SanPhamDTO> findAllReceipt(@RequestParam Integer page, @PathVariable(value = "id",required = false) Long id){
        Pageable pageable = PageRequest.of(page-1,2);
        return this.sanPhamService.findAllByPage(pageable,id);
    }


}
