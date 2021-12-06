package com.example.luonghuong.api.admin;


import com.example.luonghuong.dto.request.AnhRequest;
import com.example.luonghuong.service.AnhService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/admin/api")
@RequiredArgsConstructor
public class LuuAnhAPI {

    private final AnhService anhService;
    @PostMapping("/img")
    public String saveImg(@Valid AnhRequest anhRequest) throws IOException {
        String thumb = this.anhService.luuAnh(anhRequest);
        return thumb;
    }
}
