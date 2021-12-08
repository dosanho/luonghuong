package com.example.luonghuong.service.impl;

import com.example.luonghuong.entity.SanPhamDacTrungEntity;
import com.example.luonghuong.entity.SanPhamEntity;
import com.example.luonghuong.repository.SanPhamDacTrungRepository;
import com.example.luonghuong.repository.SanPhamRepository;
import com.example.luonghuong.service.SanPhamDacTrungService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamDacTrungServiceImpl implements SanPhamDacTrungService {

    private final SanPhamRepository sanPhamRepository;
    private final SanPhamDacTrungRepository sanPhamDacTrungRepository;
    @Override
    public List<Long> layMaDacTrungDaChon(Long id) {
        //kết quả trả về


        // tìm sản phẩm theo id
        SanPhamEntity sanPhamEntity = this.sanPhamRepository.findById(id).get();

        //tìm ra các sản phẩm đặc trưng
        List<Long> sanPhamDacTrungEntities = this
                .sanPhamDacTrungRepository.getDacTrungSanPhm(sanPhamEntity);

        return sanPhamDacTrungEntities;
    }
}
