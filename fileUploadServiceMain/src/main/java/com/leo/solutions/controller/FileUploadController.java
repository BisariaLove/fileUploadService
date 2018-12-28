package com.leo.solutions.controller;
/*
 * @author love.bisaria on 25/12/18
 */


import com.leo.solutions.domain.FileQueuedResponse;
import com.leo.solutions.service.FileUploadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Inject
    FileUploadService fileUploadService;


    @RequestMapping(value = "/upload", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public FileQueuedResponse uploadFile(){

        fileUploadService.uploadFile("Order_Level_Reversal_Template.csv", "/Users/love.bisaria/Downloads");
        return fileUploadService.createFileUploadJob("Order_Level_Reversal_Template.csv", "/Users/love.bisaria/Downloads");

    }
}
