package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.response.DacTrungDTO;
import com.example.luonghuong.entity.DacTrungEntity;
import com.example.luonghuong.repository.DacTrungRepository;
import com.example.luonghuong.service.DacTrungService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DacTrungServiceImpl implements DacTrungService {

    private final DacTrungRepository dacTrungRepository;

    @Override
    public Map<String,List<DacTrungDTO>> getDacTrung() {
        // tạo ra map kết quả để trả về
        Map<String,List<DacTrungDTO>> resultDTO = new HashMap<>();
        // lấy ra các loại đặc trung trong bảng đặc trưng(màu,size,....)
        HashSet<String> loaiDacTrung = this.dacTrungRepository.getLoaiDacTrungHQL();
        // lặp hashSet để tìm và add đặc trưng vào resultDTO
        loaiDacTrung.forEach(e->{
            //tìm ra tất cả đặc trưng có cùng loaiDacTrung
            List<DacTrungEntity> dacTrungEntities = this.dacTrungRepository.findAllByLoaiDacTrung(e);

            //tạo ra dacTrungDTOS lưu kết quả convert từ entity->dto
            List<DacTrungDTO> dacTrungDTOS = new ArrayList<>();
            // sử dùng mẫu builder convert Dto và lấy ra các thành phần cần dùng
            //lặp dacTrungEntities convert từng dactrung
            dacTrungEntities.forEach(dacTrung->{
                //Tạo đối Tương DTO;
                DacTrungDTO dacTrungDTO = DacTrungDTO.builder()
                                        .id(dacTrung.getId())
                                        .loaiDacTrung(dacTrung.getLoaiDacTrung())
                                        .ten(dacTrung.getTen()).build();
                //add dacTrungDTOS
                dacTrungDTOS.add(dacTrungDTO);
            });
            // add vào resultDTO với e là tên loại đặc trưng.
            // dacTrungDTOS là danh sách các đặc trưng.
            resultDTO.put(e,dacTrungDTOS);
        });
        return resultDTO;
    }
}
