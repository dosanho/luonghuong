package com.example.luonghuong.controller.admin;

import com.example.luonghuong.service.DacTrungService;
import com.example.luonghuong.service.LoaiSPService;
import com.example.luonghuong.service.SanPhamService;
import com.example.luonghuong.service.ThuongHieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class SanPhamController {
    // dependency inject
    private final ThuongHieuService thuongHieuService;
    // dependency inject
    private final LoaiSPService loaiSPService;
    // dependency inject
    private final SanPhamService sanPhamService;
    // dependency inject
    private final DacTrungService dacTrungService;

    @GetMapping
    public String getProduct(){
        return "views/admin/product/show_product";
    }
    @GetMapping("/add")
    public String getPageAddProduct(Model model){
        // gắn danh sách thương hiệu ra gắn vào biến tên là "thuonghieu"
        // để xong views sử dụng
        model.addAttribute("thuonghieu",thuongHieuService.findAll());
        model.addAttribute("loaispcha",loaiSPService.getLoaiSPByParentId(null));
        model.addAttribute("sanphamcha",this.sanPhamService.getProductOne());
        model.addAttribute("dactrung",this.dacTrungService.getDacTrung());

        return "views/admin/product/add_product";
    }
}
