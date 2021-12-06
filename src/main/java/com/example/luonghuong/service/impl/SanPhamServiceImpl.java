package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.request.SanPhamRequest;
import com.example.luonghuong.dto.response.SanPhamDTO;
import com.example.luonghuong.entity.*;
import com.example.luonghuong.repository.*;
import com.example.luonghuong.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {
    //dependecy inject
    private final SanPhamRepository sanPhamRepository;

    //dependecy inject
    private final LoaiSPRepository loaiSPRepository;

    //dependecy inject
    private final ThuongHieuRepository thuongHieuRepository;

    //dependecy inject
    private final SanPhamDacTrungRepository sanPhamDacTrungRepository;

    //dependecy inject
    private final DacTrungRepository dacTrungRepository;

    @Override
    public List<SanPhamDTO> getProductOne() {
        // lấy ra hết sản phẩm cha
        List<SanPhamEntity> sanPhamCha= this.sanPhamRepository.findAllByMaSanPhamChaSP(null);

        List<SanPhamEntity> sanPhamEntities = new ArrayList<>();
        for (int i = 0; i< sanPhamCha.size(); i++){
            sanPhamEntities.add(sanPhamCha.get(i));
            // tìm xem có sản phẩm con ko
            List<SanPhamEntity> check = this.sanPhamRepository.findAllByMaSanPhamChaSP(sanPhamCha.get(i));
            if (check.size() != 0){
                check.forEach(e->{
                    e.setTenSanPham("----"+e.getTenSanPham());
                });
                sanPhamEntities.addAll(check);

            }
        }
        // chuyển entity -> dto (chỉ lấy ra trường cần thiết)
        List<SanPhamDTO> results = new ArrayList<>();
        sanPhamEntities.forEach(e->{
            /// sử dung mẫu builder tạo ra DTO và lấy ra giá trị cần thiết
            SanPhamDTO dto = SanPhamDTO.builder().id(e.getId()).tenSanPham(e.getTenSanPham()).build();
            // add vào results
            results.add(dto);
        });
        return results;
    }

    @Override
    public Long themSanPham(SanPhamRequest request) {
//        tạo 1 đối tương sanPhamEntity
        SanPhamEntity sanPhamEntity = new SanPhamEntity();

        sanPhamEntity.setTenSanPham(request.getTenSanPham());
        sanPhamEntity.setAnh(request.getAnh());
        sanPhamEntity.setTrangThai(request.getTrangThai());
        sanPhamEntity.setGiaBan(request.getGiaBan());
        sanPhamEntity.setGiaNhap(request.getGiaNhap());
        sanPhamEntity.setGiamGia(request.getGiamGia());
//        tim ra sản phẩm tra và set vào sanPhamEntity
        if (request.getSanPhamCha() != null){
            SanPhamEntity sanPhamCha = this.sanPhamRepository.findById(request.getSanPhamCha()).get();
            sanPhamEntity.setMaSanPhamChaSP(sanPhamCha);
        }


//        tìm thương hiệu đã chọn
//        set vào sanPhamEntity
        ThuongHieuEntity thuongHieuEntity = thuongHieuRepository.findById(request.getThuongHieu()).get();
        sanPhamEntity.setMaThuongHieuSP(thuongHieuEntity);

//      tìm loại sản phẩm đã chọn
//        set vào sanPhamEntity
        LoaiSPEntity loaiSPEntity = loaiSPRepository.findById(request.getLoaiSP()).get();
        sanPhamEntity.setMaLoaiSanPhamSP(loaiSPEntity);
//        insert giá trị vào bẳng sản phẩm và lấy ra dùng tiếp
        SanPhamEntity result = sanPhamRepository.save(sanPhamEntity);

//        Thêm vào bảng trung gian đặc trưng sản phẩm
//         lặp qa id của bảng đặc trưng gửi lên
        request.getLoaiDacTrung().forEach(e->{
//            tìm ra đặc trưng bằng id vừa gửi lên
            DacTrungEntity dacTrung = this.dacTrungRepository.findById(e).get();
//            tạo đối tưởng đặc trưng sản phẩm
            SanPhamDacTrungEntity sanPhamDacTrungEntity = new SanPhamDacTrungEntity();
//            set các trường của dối tưởng
            sanPhamDacTrungEntity.setMaSanPham(result);
            sanPhamDacTrungEntity.setMaDacTrung(dacTrung);
            sanPhamDacTrungEntity.setMoTa(dacTrung.getTen());
//            thực hiện insert giá trị vào bảng đặc trưng sản phẩm trong database
            sanPhamDacTrungRepository.save(sanPhamDacTrungEntity);
        });
        return result.getId();
    }

    @Override
    public Integer tinhTrang(Long id) {
        if (id != null){
            SanPhamEntity sanPhamEntity = this.sanPhamRepository.findById(id).get();
            return (int)Math.ceil(((float)sanPhamRepository.countByMaSanPhamChaSP(sanPhamEntity))/2);
        } else {
            return (int)Math.ceil(((float)sanPhamRepository.countByMaSanPhamChaSP(null))/2);
        }
    }

    @Override
    public List<SanPhamDTO> findAllByPage(Pageable pageable, Long id) {
        // tạo ra list các san phẩm  kiểu entity
        Page<SanPhamEntity> pageResultEntity = null;
        if (id != null){
            // tìm ra sản phẩm bằng id chuyển vào nếu id là khác null
            SanPhamEntity sanPhamEntity = this.sanPhamRepository.findById(id).get();
            // tìm ra cách sản phẩm con của nó rồi add bằng list sanPhamEntities
            pageResultEntity = this.sanPhamRepository.findAllByMaSanPhamChaSP(pageable,sanPhamEntity);
        } else {
            // nếu id truyền vào null tìm ra các sửa phẩn có mã sản phẩn cha bằng null
            pageResultEntity =  this.sanPhamRepository.findAllByMaSanPhamChaSP(pageable,null);
        }
        // tạo ra list dto lưu chữ kết quá
        List<SanPhamDTO> sanPhamDTOS = new ArrayList<>();
        // sửa dùng mẫu bulder để chuyển từ entity -> dto
        pageResultEntity.forEach(e->{
            SanPhamDTO dto = SanPhamDTO.builder()
                    .id(e.getId())
                    .tenSanPham(e.getTenSanPham())
                    .anh(e.getAnh()).tenLoai(e.getMaLoaiSanPhamSP().getTenLoaiSP())
                    .tenThuongHieu(e.getMaThuongHieuSP().getTenThuongHieu())
                    .giaBan(e.getGiaBan()).giaNhap(e.getGiaNhap())
                    .trangThai(e.getTrangThai()).build();
            // sau đó add list dto "sanPhamDTOS"
            sanPhamDTOS.add(dto);
        });
        // trả về list sanPhamDTOS
        return sanPhamDTOS;
    }



}
