package com.mcp.mcpserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class FileService {
    @Value("${file.base.upload-dir}")
    private String baseDir;

    public String readFile(String path) throws IOException {
        Path filePath = Paths.get(baseDir, path);
        return Files.readString(filePath);
    }

    public void writeFile(String path, String content) throws IOException {
        Path filePath = Paths.get(baseDir, path);
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public void deleteFile(String path) throws IOException {
        Path filePath = Paths.get(baseDir, path);
        Files.deleteIfExists(filePath);
    }

    public void saveUploadedFile(String relativePath, byte[] bytes) throws IOException {
        Path filePath = Paths.get(baseDir, relativePath);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, bytes);
    }
}
