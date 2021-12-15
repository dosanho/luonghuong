package com.example.luonghuong.controller.admin;

import com.example.luonghuong.service.ThuongHieuService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ThuongHieuController {

    private  final  ThuongHieuService thuongHieuService;
    @GetMapping("/brand")
    public String getAllBrand(){
        return "/views/admin/brandproduct/show_brandproduct";
    }

    @GetMapping({"/brand/add","/brand/add/{id}"})
    public String getAddBrand(Model model, @PathVariable(value = "id",required = false) Long id){
        if (id != null){
            model.addAttribute("banner","Sửa sản phẩm");
            // tìm sản phẩm theo id rồi gắn vào biến tên brand để sử dụng ở view
            model.addAttribute("brand", thuongHieuService.getOneById(id));
        } else {
            model.addAttribute("banner","Thêm sản phẩm");
            // thêm sửa phẩm thì đặt là false
            model.addAttribute("brand", false);
        }
        return "/views/admin/brandproduct/add_brandproduct";
    }

}
