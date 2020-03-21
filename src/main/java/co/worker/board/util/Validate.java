package co.worker.board.util;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Component
@Slf4j
public class Validate {

    private static String allowFileExtension;

    @Value("${file.extension.allow}")
    private void setAllowFileExtension(String extension){
        allowFileExtension = extension;
    }

    public static void isTrue(boolean isTrue, String msg){  //false일 때 에러를 낸다.
        if(!isTrue){
            throw new RuntimeException(msg);
        }
    }

    public static void imagesValidation(@NonNull ArrayList<MultipartFile> images){
        //확장자 -> jpg, jpeg, png, gif만 가능.

        images.forEach(image -> {
            if(image.getSize() == 0L)
                throw new RuntimeException("사이즈가 0인 이미지는 업로드할 수 없습니다.");

            String fileName = image.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

            if (!allowFileExtension.contains(fileExtension)) {
                throw new RuntimeException("올바르지 않은 확장자 입니다. : "+fileExtension);
            }
        });

    }

    public static void filesValidation(@NonNull ArrayList<MultipartFile> files){
        files.forEach(file -> {
            if(file.getSize() == 0L)
                throw new RuntimeException("사이즈가 0인 파일은 업로드할 수 없습니다.");
        });
    }

}
