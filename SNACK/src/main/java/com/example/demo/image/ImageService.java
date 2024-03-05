package com.example.demo.image;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    static final String SEP = File.separator;
    static final String IMAGE_EXTENSION = ".png";
    static final String PATH = SEP + "home" + SEP + "test" + SEP + "images" + SEP;
//    static final String PATH = "D:\\test\\";


    /*
     * 1개 이상의 이미지를 저장하는 메서드
     *
     * @param List<MultipartFile>
     * @return 이미지명 리스트
     */
    public List<String> uploadImages(List<MultipartFile> multipartFiles) throws IOException {
        log.info("excute deleteImages ");
        List<String> imageNames = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            imageNames.add(uploadImage(multipartFile));
        }
        return imageNames;
    }

    /*
     * 단일 파일 업로드
     */
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        // 로컬 테스트용
//        String uploadPath = "C:\\Users\\손영호\\IdeaProjects\\image\\" + dateFormat.format(now) + FILE_EXTENSION;

        String imageName = createNewImageName();

        File uploadFile = new File(PATH + imageName);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new IOException(e);
        }

        return imageName;
    }

    /*
     * 서버에 저장될 이미지 명을 생성하는 메서드 (중복 테스트 X, ms 단위에서 파일명 충복 발생시 파일명 생성 UUID로)
     *
     * @return yyyyMMdd_HHmmss_SSSS.png 형태의 String
     */
    public String createNewImageName() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSSS");
        return dateFormat.format(now) + IMAGE_EXTENSION;
    }

    /*
     * 기존에 저장되어있던 이미지를 삭제하는 메서드
     *
     * @param yyyyMMdd_HHmmss_SSSS.png 등으로 저장 되어 있는 이미지명 리스트
     */
    public void deleteImages(List<String> imageNames) {
        log.info("excute deleteImages ");
        for (String imagePath : imageNames) {
            File image = new File(PATH + imagePath);

            if (image.exists()) {
                log.info("deleteImages name : " + image.getName());
                image.delete();
            }
        }
    }

    /*
     * 기존에 저장된 이미지를 새로운 이미지로 변경하는 메서드
     *
     * @param yyyyMMdd_HHmmss_SSSS.png 형태로 저장 되어 있는 이미지명 리스트
     * @param MultipartFile 형태의 이미지 파일 리스트
     */
    public List<String> updateImages(List<String> imageNames, List<MultipartFile> multipartFiles) throws IOException {
        log.info("excute updateImages ");
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            deleteImages(imageNames);
            log.info("complete deleteImages ");
        });

        return uploadImages(multipartFiles);
    }

    private void valiedDirectory() {
        File dir = new File(PATH);

        if(dir.exists() == false)
            dir.mkdirs();
    }
}