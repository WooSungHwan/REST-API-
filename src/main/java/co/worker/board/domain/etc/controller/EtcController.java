package co.worker.board.domain.etc.controller;

import co.worker.board.domain.etc.service.EtcService;
import co.worker.board.domain.user.model.SecurityUser;
import co.worker.board.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/upload")
public class EtcController {

    @Autowired
    private EtcService etcService;

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object image(@RequestParam("images") ArrayList<MultipartFile> images, @AuthenticationPrincipal SecurityUser user) {
        Validate.imagesValidation(images);
        return etcService.image(images);
    }

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object files(@RequestParam("files") ArrayList<MultipartFile> files, @AuthenticationPrincipal SecurityUser user) {
        Validate.filesValidation(files);
        return etcService.file(files);
    }
}
