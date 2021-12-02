package ru.javastripters.onboarder.controller;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.javastripters.onboarder.service.FilesService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Server
@RestController
@RequestMapping("files")
public class FilesController {
    private final FilesService imageService;

    public FilesController(FilesService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(
            @RequestParam MultipartFile file,
            @RequestParam(defaultValue = "") String path
    ) {
        imageService.saveFile(file, path);
    }

    @GetMapping(value = "**", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request) throws IOException {
        String filePath = request.getRequestURI();
        // Remove "/files" from path
        filePath = filePath.substring(filePath.indexOf('/', filePath.indexOf('/') + 1));
        FilesService.SendableFile file = imageService.getFile(filePath);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(file.mediaType()))
                .body(file.raw());
    }
}
