package com.example.luonghuong.service.impl;

import com.example.luonghuong.dto.request.AnhRequest;
import com.example.luonghuong.img.LuuAnhVaoThuMuc;
import com.example.luonghuong.service.AnhService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AnhServiceImpl implements AnhService {
    private final HttpServletRequest request;

    @Value("${image.location}")
    private String location;

    @Override
    public String luuAnh(AnhRequest anhRequest) throws IOException  {
        return LuuAnhVaoThuMuc.luuAnh(anhRequest.getImg(),anhRequest.getBaseUrl(),this.location,request);
    }
}
