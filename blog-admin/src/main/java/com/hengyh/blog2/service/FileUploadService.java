package com.hengyh.blog2.service;

import java.io.InputStream;
import java.util.Map;

public interface FileUploadService {
    Map<String, String> imageUpload(InputStream inputStream, String directory);
}
