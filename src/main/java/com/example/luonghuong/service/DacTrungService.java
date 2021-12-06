package com.example.luonghuong.service;

import com.example.luonghuong.dto.response.DacTrungDTO;


import java.util.List;
import java.util.Map;

public interface DacTrungService {
    Map<String, List<DacTrungDTO>> getDacTrung();
}
