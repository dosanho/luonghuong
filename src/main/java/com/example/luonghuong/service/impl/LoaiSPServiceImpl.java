package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.response.LoaiSPDTO;
import com.example.luonghuong.entity.LoaiSPEntity;
import com.example.luonghuong.repository.LoaiSPRepository;
import com.example.luonghuong.service.LoaiSPService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoaiSPServiceImpl implements LoaiSPService {
    private final LoaiSPRepository loaiSPRepository;
    @Override
    @Transactional
    public List<LoaiSPDTO> getLoaiSPByParentId(Long id) {
        List<LoaiSPEntity> loaiSPEntityList = new ArrayList<>();
        /// lấy ra hết các sản phẩm cha (ma loai sp cha != null)
        if (id != null){
            LoaiSPEntity loaiSPEntity = this.loaiSPRepository.findById(id).get();
            loaiSPEntityList = this.loaiSPRepository.findAllByMaLoaiSPCha(loaiSPEntity);
        } else {
            /// lấy ra hết các sản phẩm cha (ma loai sp cha == null)
            loaiSPEntityList = this.loaiSPRepository.findAllByMaLoaiSPCha(null);
        }

        //tại mảng results để lưu kết trả về dạng dto
        List<LoaiSPDTO> results = new ArrayList<>();
        // lặp mảng chứa các loại sản phẩm cha dạng entity vừa lấy ra bên trên
        // chuyển đổi về dạng dto
        loaiSPEntityList.forEach(e->{
            // chuyển đồi entity -> dto sử dụng mẫu builder
            LoaiSPDTO loaiSPDTO = LoaiSPDTO.builder().id(e.getId()).tenLoaiSP(e.getTenLoaiSP()).build();
            results.add(loaiSPDTO);
        });
        return results;
    }
}
