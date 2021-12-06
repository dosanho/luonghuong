package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.response.ThuongHieuDTO;
import com.example.luonghuong.entity.ThuongHieuEntity;
import com.example.luonghuong.repository.ThuongHieuRepository;
import com.example.luonghuong.service.ThuongHieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThuongHieuServiceImpl implements ThuongHieuService {
    private final ThuongHieuRepository thuongHieuRepository;
    @Override
    public List<ThuongHieuDTO> findAll() {
        // lấy hết thông tin từ bảng ThuongHieu
        List<ThuongHieuEntity> thuongHieuEntities = this.thuongHieuRepository.findAll();

        //tạo 1 danh sách để chứa kết quả trả về
        List<ThuongHieuDTO> result = new ArrayList<>();
        // duyệt danh sách thương hiệu vừa lấy . để chuyển về DTO(chỉ lấy những thông tin cần thiết)
        thuongHieuEntities.forEach(e->{
            // lấy ra id vs tên thương hiệu của từ thương hiệu . sử dung mẫu builder
            ThuongHieuDTO thuongHieuDTO = ThuongHieuDTO.builder().id(e.getId())
                                .tenThuongHieu(e.getTenThuongHieu()).build();
            // add vào vào danh sách kq
            result.add(thuongHieuDTO);

        });
        // trả về danh sách result
        return result;
    }
}
