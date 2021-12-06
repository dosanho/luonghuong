package com.example.luonghuong.service;

import com.example.luonghuong.dto.request.AnhRequest;

import java.io.IOException;

public interface AnhService {
    String luuAnh(AnhRequest anhRequest) throws IOException;
}
