package com.example.luonghuong.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AnhRequest {
    private String baseUrl;
    private MultipartFile img;
}
