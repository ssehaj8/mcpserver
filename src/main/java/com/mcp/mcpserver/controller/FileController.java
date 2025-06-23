package com.mcp.mcpserver.controller;

import com.mcp.mcpserver.Dto.FileRequest;
import com.mcp.mcpserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files,
                                         @RequestParam("paths") String[] paths) throws IOException {
        for (int i = 0; i < files.length; i++) {
            fileService.saveUploadedFile(paths[i], files[i].getBytes());
        }
        return ResponseEntity.ok("Files uploaded");
    }

    @GetMapping("/read")
    public ResponseEntity<String> readFile(@RequestParam String path) throws IOException {
        String content = fileService.readFile(path);
        return ResponseEntity.ok(content);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editFile(@RequestBody FileRequest req) throws IOException {
        fileService.writeFile(req.getPath(), req.getContent());
        return ResponseEntity.ok("File updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFile(@RequestParam String path) throws IOException {
        fileService.deleteFile(path);
        return ResponseEntity.ok("File deleted");
    }
}
