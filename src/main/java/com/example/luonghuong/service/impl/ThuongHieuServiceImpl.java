package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.response.ThuongHieuDTO;
import com.example.luonghuong.entity.ThuongHieuEntity;
import com.example.luonghuong.repository.SanPhamRepository;
import com.example.luonghuong.repository.ThuongHieuRepository;
import com.example.luonghuong.service.SanPhamService;
import com.example.luonghuong.service.ThuongHieuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThuongHieuServiceImpl implements ThuongHieuService {

    private final ThuongHieuRepository thuongHieuRepository;

    private final SanPhamRepository sanPhamRepository;

    private final SanPhamService sanPhamService;

    // lấy hết thương hiệu bảng thương hiệu -> dto rồi trả về
    @Override
    public List<ThuongHieuDTO> findAll() {
        // lấy hết thông tin từ bảng ThuongHieu dạng entity
        List<ThuongHieuEntity> thuongHieuEntities = this.thuongHieuRepository.findAll();


        //tạo 1 danh sách để chứa kết quả trả về
        List<ThuongHieuDTO> result = new ArrayList<>();
        // duyệt danh sách thương hiệu vừa lấy . để chuyển về DTO(chỉ lấy những thông tin cần thiết)
        thuongHieuEntities.forEach(e->{
            // lấy ra id vs tên thương hiệu của từ thương hiệu . sử dung mẫu builder
            ThuongHieuDTO thuongHieuDTO = ThuongHieuDTO.builder()
                                .id(e.getId())
                                .tenThuongHieu(e.getTenThuongHieu()).build();

            // add vào vào danh sách kq
            result.add(thuongHieuDTO);

        });
        // trả về danh sách result
        return result;
    }
    //tinh tổng trang/////
    @Override
    public Integer totalPage() {
        return (int)Math.ceil(((float)thuongHieuRepository.count())/5);
    }
    //phân trang///
    @Override
    public List<ThuongHieuDTO> getBrandByPage(Pageable pageable) {
        // lấy hết thông tin từ bảng ThuongHieu dạng entity
        Page<ThuongHieuEntity> thuongHieuEntities = this.thuongHieuRepository.findAll(pageable);


        //tạo 1 danh sách để chứa kết quả trả về
        List<ThuongHieuDTO> result = new ArrayList<>();
        // duyệt danh sách thương hiệu vừa lấy . để chuyển về DTO(chỉ lấy những thông tin cần thiết)
        thuongHieuEntities.forEach(e->{
            // lấy ra id vs tên thương hiệu của từ thương hiệu . sử dung mẫu builder
            ThuongHieuDTO thuongHieuDTO = ThuongHieuDTO.builder()
                    .id(e.getId())
                    .tenThuongHieu(e.getTenThuongHieu())
                    .hinhAnh(e.getHinhAnh())
                    .moTa(e.getMoTa())
                    .trangThai(e.getTrangThai())
                    .build();
            // add vào vào danh sách kq
            result.add(thuongHieuDTO);
      });
        return result;
    }

    @Override
    public Long addOrEditBrand(ThuongHieuDTO thuongHieuDTO) {
        // khai báo 1 biến thương hiệu entity
        ThuongHieuEntity thuongHieuEntity = null;
        // nếu thêm thì id == null
        if (thuongHieuDTO.getId() == null){
            // thì thêm sản entity
            thuongHieuEntity = new ThuongHieuEntity();
        } else {
            thuongHieuEntity = thuongHieuRepository.findById(thuongHieuDTO.getId()).get();
        }
        thuongHieuEntity.setTenThuongHieu(thuongHieuDTO.getTenThuongHieu());
        thuongHieuEntity.setHinhAnh(thuongHieuDTO.getHinhAnh());
        thuongHieuEntity.setMoTa(thuongHieuDTO.getMoTa());
        thuongHieuEntity.setTrangThai(thuongHieuDTO.getTrangThai());

        // lưu xong trả luôn về id của thương hiệu vừa tạo
        return thuongHieuRepository.save(thuongHieuEntity).getId();
    }

    // tìm sản phẩm theo id chuyển về dạng dto
    @Override
    public ThuongHieuDTO getOneById(Long id) {
        // tìm ra sản phẩm cần sửa theo id
        ThuongHieuEntity thuongHieuEntity = this.thuongHieuRepository.findById(id).get();
        // chuyển từ entity -> dto sử dùng mẫu builder
        ThuongHieuDTO thuongHieuDTO = ThuongHieuDTO.builder()
                .id(thuongHieuEntity.getId())
                .tenThuongHieu(thuongHieuEntity.getTenThuongHieu())
                .trangThai(thuongHieuEntity.getTrangThai())
                .hinhAnh(thuongHieuEntity.getHinhAnh())
                .moTa(thuongHieuEntity.getMoTa())
                .build();
        return thuongHieuDTO;
    }

    @Override
    public Boolean deleteBrand(Long id) {

            // tìm sản phẩm
            List<Long> sanPhamListId =  this.sanPhamRepository.getIdByMaThuongHieuAndMaSanPhamCha(id);
            sanPhamListId.forEach(e->{
                        // xoá sản phẩm có thương hiệu muốn xoá
                        sanPhamService.xoaSanPham(e);
                    });
            // xoá thương hiệu đó
            thuongHieuRepository.deleteById(id);
            return true;

        }

    }

