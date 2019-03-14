package com.hengyh.blog2.service.impl;

import com.hengyh.blog2.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileUploadImpl implements FileUploadService {
    @Value("${file.upload.tempLocation}")
    String uploadLocation;

    @Override
    public Map<String, String> imageUpload(InputStream inputStream, String directory) {
        Map<String, String> retMap = new HashMap<>();
        String fileName = UUID.randomUUID().toString() + ".png";
        try(inputStream){
            uploadToLocal(inputStream,
                    directory + uploadLocation,
                    fileName);
            retMap.put("url", uploadLocation + File.separator + fileName);
            retMap.put("code", "success");
        } catch (IOException e) {
            retMap.put("code", "fail");
        }
        return retMap;
    }

    private Resource uploadToLocal(InputStream inputStream, String targetDir, String fileName) throws IOException {
        Path path = Paths.get(targetDir);
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (FileAlreadyExistsException ignore) {}
        }
        Path newFile = Files.createFile(Paths.get(targetDir + File.separator + fileName));
        try(inputStream;
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile.toFile()))) {
            int s;
            while ((s = bis.read()) != -1) {
                bos.write(s);
            }
        }
        return new FileSystemResource(newFile.toFile());
    }

//    public static void main(String[] args) throws IOException {
//        FileInputStream inputStream =
//                new FileInputStream(new File("C:\\Users\\hengy\\Pictures\\Screenshots\\1.png"));
//        new FileUploadImpl().uploadToLocal(inputStream, "C:\\Users\\hengy\\Pictures\\Screenshots\\test", "1.png");
//    }
}
