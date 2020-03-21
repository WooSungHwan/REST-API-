package co.worker.board.domain.etc.service;

import co.worker.board.domain.etc.repository.EtcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

@Service
public class EtcService {

    @Autowired
    private EtcRepository etcRepository;

    @Value("${file.destination}")
    private String destinationFolderName;

    public Object image(ArrayList<MultipartFile> files) {
        //없으면 생성
        File destinationFoler = new File(destinationFolderName);
        if (!destinationFoler.exists()) {
            destinationFoler.mkdir();
        }

        //이미지들 포뤂
        files.forEach(file -> {
            try {
                String transferFileName = destinationFolderName+"\\"+file.getOriginalFilename();
                file.transferTo(new File(transferFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return null;
    }

    public Object file(ArrayList<MultipartFile> files) {
        //없으면 생성
        File destinationFoler = new File(destinationFolderName);
        if (!destinationFoler.exists()) {
            destinationFoler.mkdir();
        }

        //파일들 포뤂
        files.forEach(file -> {
            try {
                String transferFileName = destinationFolderName+"\\"+file.getOriginalFilename();
                file.transferTo(new File(transferFileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return null;
    }
}
