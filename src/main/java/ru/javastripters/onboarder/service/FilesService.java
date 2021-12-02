package ru.javastripters.onboarder.service;

import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class FilesService {
    public static record SendableFile(byte[] raw, String mediaType) {}

    private static final String filesDir = "files";

    public FilesService() throws IOException {
        Files.createDirectories(Path.of(filesDir));
    }

    @SneakyThrows
    public void saveFile(MultipartFile file, String path) {
        Objects.requireNonNull(file.getOriginalFilename(), "Filename is null!");

        Path dir = Path.of(filesDir, path);
        Files.createDirectories(dir);

        file.transferTo(dir.resolve(file.getOriginalFilename()));
    }

    @SneakyThrows
    public SendableFile getFile(String filename) {
        Path path = Path.of(filesDir, filename);
        if (!path.toFile().exists())
            throw new FileNotFoundException("File: " + path.getFileName() + " not found!");
        return new SendableFile(Files.readAllBytes(path), Files.probeContentType(path));
    }
}
