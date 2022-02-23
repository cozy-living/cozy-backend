package com.cozy.controller;

import com.cozy.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage/")
public class BucketController {

    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    /**
     * uploadFile: Uploads a file (image/video whose size is less than 10 MB) to AWS S3 bucket;
     *             needs to pass in the file to be uploaded
     * Method: POST
     * Endpoint: /storage/uploadFile
     */
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }

    /**
     * deleteFile: Delete an existing file from AWS S3 bucket;
     *             needs to pass in the url of the file
     * Method: DELETE
     * Endpoint: /storage/deleteFile
     */
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}