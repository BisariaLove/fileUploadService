package com.leo.solutions.service;
/*
 * @author love.bisaria on 24/12/18
 */

import com.leo.solutions.domain.FileQueuedResponse;
import org.springframework.stereotype.Service;

@Service
public interface FileUploadService {

    public FileQueuedResponse createFileUploadJob(String localFileName, String localFilePath);

    public boolean uploadFile(String fileName, String filePath) ;
}
