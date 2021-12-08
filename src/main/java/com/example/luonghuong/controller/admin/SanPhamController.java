package com.example.luonghuong.controller.admin;

import com.example.luonghuong.dto.response.SanPhanSuaDTO;
import com.example.luonghuong.entity.DacTrungEntity;
import com.example.luonghuong.repository.DacTrungRepository;
import com.example.luonghuong.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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

    // dependency inject
    private final SanPhamDacTrungService sanPhamDacTrungService;

    // dependency inject
    private final DacTrungRepository dacTrungRepository;

    @GetMapping
    public String getProduct(){
        return "views/admin/product/show_product";
    }
    @GetMapping({"/add","/add/{id}"})
    public String getPageAddProduct(Model model,@PathVariable(value = "id",required = false) Long id){

        // gắn danh sách thương hiệu ra gắn vào biến tên là "thuonghieu"
        // để xong views sử dụng
        model.addAttribute("thuonghieu",thuongHieuService.findAll());
        model.addAttribute("loaispcha",loaiSPService.getLoaiSPByParentId(null));
        model.addAttribute("sanphamcha",sanPhamService.getProductOne());
        model.addAttribute("dactrung",dacTrungService.getDacTrung());

        // nếu id khác null thì tìm sản phẩm id vừa truyền vào rồi gắn nó vào biến "sanpham"
        // ngược lại gắp là false không có.
        if (id != null){
            SanPhanSuaDTO sanPhanSuaDTO = sanPhamService.findById(id);
            model.addAttribute("sanpham",sanPhanSuaDTO);
            /*loại sản phẩn con*/
            model.addAttribute("loaispcon",loaiSPService.getLoaiSPByParentId(sanPhanSuaDTO.getMaLoaiCha()));
            /*lấy ra sản phẩm đặc trưng của sản phẩm muốn sửa*/
            model.addAttribute("check",
                    this.checkDacTrung(dacTrungRepository.findAll(),sanPhamDacTrungService.layMaDacTrungDaChon(id)));
            model.addAttribute("tacvu","Sửa sản phẩm");
        } else {
            model.addAttribute("sanpham",false);
            model.addAttribute("tacvu","Thêm sản phẩm");
        }
        return "views/admin/product/add_product";
    }

//    kiểm tra nếu có chọn đặc trung thì true không false
    public static Map<Long,Boolean> checkDacTrung(List<DacTrungEntity>dacTrungEntityMap,
                                                      List<Long> dacTrungId){
        Map<Long,Boolean> results = new HashMap<>();
        for (int i = 0; i< dacTrungEntityMap.size(); i++){
            boolean check = false;
            for (int j=0; j< dacTrungId.size(); j++){
                if (dacTrungEntityMap.get(i).getId() == dacTrungId.get(j)){
                    results.put(dacTrungEntityMap.get(i).getId(),true);
                    check = true;
                }
            }
            if(check == false){
                results.put(dacTrungEntityMap.get(i).getId(),false);
            }
        }
        return results;

    }
}
