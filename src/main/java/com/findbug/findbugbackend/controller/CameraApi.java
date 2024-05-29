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

@RestController
@Slf4j
public class CameraApi {

    private final CameraService cameraService;

    @Autowired
    public CameraApi(CameraService cameraService){
        this.cameraService = cameraService;
    }

    @PostMapping("/upload/{imei}")
    public String uploadImage(
            @RequestParam("file")MultipartFile file,
            @PathVariable("imei") String imei
    ){

        try {
            return cameraService.uploadImage(imei, file);
        }catch (Exception e) {
            return "error";
        }
    }
}
