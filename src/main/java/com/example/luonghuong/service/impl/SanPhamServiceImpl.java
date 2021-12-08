package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.request.SanPhamRequest;
import com.example.luonghuong.dto.response.SanPhamDTO;
import com.example.luonghuong.dto.response.SanPhanSuaDTO;
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

        SanPhamEntity sanPhamEntity = null;
        // nếu id == null thì   tạo 1 đối tương sanPhamEntity
        if (request.getId() == null){
            sanPhamEntity  = new SanPhamEntity();
        }
        //        nếu id != null thì  thì đối tương id đó
        else {
            sanPhamEntity = this.sanPhamRepository.findById(request.getId()).get();
            //   xoá hết đặc trung sản phẩm đi
            this.sanPhamDacTrungRepository.deleteAllByMaSanPham(sanPhamEntity);

        }

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
//        nếu = null set nut
        else {
            sanPhamEntity.setMaSanPhamChaSP(null);
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
        //trả về id
        return result.getId();
    }

//    xoá sản phẩm
    @Override
    public void xoaSanPham(Long id) {
        // tìm sản phẩm có id truyền vào
        SanPhamEntity sanPhamEntity = this.sanPhamRepository.findById(id).get();
    }


    @Override
    public Integer tinhTrang(Long id) {
        if (id != null){
            SanPhamEntity sanPhamEntity = this.sanPhamRepository.findById(id).get();
            return (int)Math.ceil(((float)sanPhamRepository.countByMaSanPhamChaSP(sanPhamEntity))/6);
        } else {
            return (int)Math.ceil(((float)sanPhamRepository.countByMaSanPhamChaSP(null))/6);
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

    @Override
    public SanPhanSuaDTO findById(Long id) {
//        tìm Sản phẩm dạng Entity theo id
        SanPhamEntity entity = this.sanPhamRepository.findById(id).get();
//        tìm tên loại sản phẩm con

//        sử dụng mẫu builder dto để chuyển từ entity->dto
        SanPhanSuaDTO dto = SanPhanSuaDTO.builder()
                .id(entity.getId())
                .tenSanPham(entity.getTenSanPham())
                .anh(entity.getAnh())
                .maThuongHieu(entity.getMaThuongHieuSP().getId())
                .giaBan(entity.getGiaBan())
                .giaNhap(entity.getGiaNhap())
                .trangThai(entity.getTrangThai())
                .giamGia(entity.getGiamGia())
                .maSanPhamCha(entity.getMaSanPhamChaSP() == null ? null :entity.getMaSanPhamChaSP().getId())
                .maLoaiCon(entity.getMaLoaiSanPhamSP().getId())
                .maLoaiCha(entity.getMaLoaiSanPhamSP().getMaLoaiSPCha().getId())
                .build();
        return dto;
    }


}
