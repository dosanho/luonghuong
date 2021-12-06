package com.example.luonghuong.img;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


public class LuuAnhVaoThuMuc {
    public static String luuAnh(MultipartFile file, String check, String location, HttpServletRequest request)
            throws IOException {
        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
        //tạo tên ảnh
        String fileName = "";
        if (check.equals("")){
            fileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        } else {
            String[] test = check.split("/");
            fileName = test[test.length - 1];
        }
        //tạo folder chứa ảnh
        Path path = Paths.get(location);
        Files.createDirectories(path);
        //lưu folder ảnh vào file vừa tạo
        File saveFile = Paths.get(location,fileName).toFile();
        file.transferTo(saveFile);
        return baseUrl + "/img/" + fileName;
    }
}
