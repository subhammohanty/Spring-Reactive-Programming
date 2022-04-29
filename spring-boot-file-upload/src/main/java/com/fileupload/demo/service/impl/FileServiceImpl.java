package com.fileupload.demo.service.impl;

import com.fileupload.demo.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        //File Name - from request file.
        String fileName = file.getOriginalFilename();
        //FullPath - from properties file + fileName;
        String randomID = UUID.randomUUID().toString();
        String fileName1 = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));

        String filePath = path + File.separator + fileName1;
        //create a folder if not present
        File file1 = new File(path);
        if(!file1.exists()){
            file1.mkdir();
        }
        //copy file
        Files.copy(file.getInputStream() , Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName){
        String fullPath = path + File.separator + fileName;
        InputStream is = null;
        try {
            is = new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;
    }
}
