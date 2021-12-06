package com.example.luonghuong.dto.request;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
public class DangKyRequest {

    @NotBlank(message = "Không đươc bỏ trống mục này")
    private String tenDangNhap;

    @NotBlank(message = "Không đươc bỏ trống mục này")
    private String matKhau;

    @NotBlank(message = "Không đươc bỏ trống mục này")
    private String matKhauLap;


    @NotBlank(message = "Không đươc bỏ trống mục này")
    private String hoTen;

    @NotBlank(message = "Không đươc bỏ trống mục này")
    private String sdt;

    @NotBlank(message = "Không đươc bỏ trống mục này")
    @Email(message = "Sai định dạng email, vui lòng kiểm tra lại!")
    private String email;

    @AssertTrue(message = "Mật khẩu không giống nhau, vui lòng kiểm tra lại")
    private boolean isValidPassword() {
        return Objects.nonNull(this.matKhau) && this.matKhau.equals(this.matKhauLap);
    }
}
