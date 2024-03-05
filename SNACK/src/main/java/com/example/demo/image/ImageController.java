package com.example.demo.image;

import jakarta.validation.constraints.NotEmpty;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public void uploadImage(Authentication authentication, @NotEmpty @RequestPart MultipartFile image) throws IOException {
        imageService.uploadImage(image);
    }
}
