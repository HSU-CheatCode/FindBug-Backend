package com.findbug.findbugbackend.controller;


import com.findbug.findbugbackend.service.CameraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CameraApi {

    private final CameraService cameraService;

    @PostMapping("/upload")
    public String uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("imei") String imei,
            @RequestParam("bugName") String bugName,
            @RequestParam("detectedTime") LocalDateTime detectedTime
    ){
        try {
            return cameraService.uploadImage(imei, bugName, detectedTime, file);
        }catch (Exception e) {
            log.error(e.getMessage());
            return "upload failed";
        }
    }
}
