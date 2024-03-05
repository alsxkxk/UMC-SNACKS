package com.example.demo.image;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    static final String SEP = File.separator;
    static final String IMAGE_EXTENSION = ".png";
    static final String PATH = SEP + "home" + SEP + "test" + SEP + "images" + SEP;

    public static MultipartFile convert(File file) throws IOException {
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile(
                file.getName(),   // 파일 이름
                file.getName(),   // Original 파일 이름
                null,             // 파일의 컨텐트 타입 (자동 감지됨)
                input             // 파일의 내용을 읽어오는 InputStream
        );
    }

    @Test
    public void deleteImagesTest() throws IOException, InterruptedException {
        String uploadPath1 = "D:\\test\\test1.png";
        String uploadPath2 = "D:\\test\\test2.png";

        File file1 = new File(uploadPath1);
        File file2 = new File(uploadPath2);

        MultipartFile m1 = convert(file1);
        MultipartFile m2 = convert(file2);

        List<MultipartFile> multipartFileList = new ArrayList<>();
        multipartFileList.add(m1);
        multipartFileList.add(m2);

        String deleteImageName1 = "delete1.PNG";
        String deleteImageName2 = "delete2.PNG";

        List<String> imageList = new ArrayList<>();
        imageList.add(deleteImageName1);
        imageList.add(deleteImageName2);

        List<String> t = imageService.updateImages(imageList, multipartFileList);

        Thread.sleep(3000);

        System.out.println(t.get(0) + " " + t.get(1));
    }
}
