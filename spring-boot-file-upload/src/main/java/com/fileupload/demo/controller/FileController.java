package com.fileupload.demo.controller;

import com.fileupload.demo.payload.FileResponse;
import com.fileupload.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile file) {
        String fileName = null;
        try {
            fileName = fileService.uploadFile(path, file);
        } catch (IOException e) {
            return  new ResponseEntity<FileResponse>(new FileResponse(fileName , "Image Upload Failed") , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FileResponse>(new FileResponse(fileName, "Image Uploaded Successfully"), HttpStatus.OK);
    }

    @GetMapping("/images/{imageName}")
    public void getResource(@PathVariable("imageName") String imageName , HttpServletResponse response) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource , response.getOutputStream());
    }
}
