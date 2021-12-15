package com.example.luonghuong.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@Builder
public class SanPhamRequest {

    private Long id;
    @NotBlank
    private String tenSanPham;

    @NotNull
    private Long loaiSP;


    private Long thuongHieu;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @PositiveOrZero
    private BigDecimal giaNhap;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @PositiveOrZero
    private BigDecimal giaBan;

    private Long sanPhamCha;

    @NotBlank(message = "nhập loại ảnh sản phẩm")
    private String anh;


    private Integer giamGia;

    @NotNull
    private Integer trangThai;


    private List<Long> loaiDacTrung;

    @AssertTrue(message = "giá nhập phải nhỏ hơn giá bán")
    private boolean isValidCheckPrice() {
        if (this.giaBan.compareTo(BigDecimal.ZERO) == 0
                && this.giaNhap.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        else {
            return Objects.nonNull(this.giaBan) &&
                    Objects.nonNull(this.giaNhap) && (this.giaNhap.compareTo(this.giaBan)<0);
        }

    }

}
