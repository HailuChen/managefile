package com.chenhailu.consumer.controller;


import com.chenhailu.consumer.constant.Constant;

import com.chenhailu.consumer.entity.ReturnMessage;
import com.chenhailu.consumer.service.UploadFileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileReadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReadController.class);

    @Autowired
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/upload_file", method = RequestMethod.POST)
    public ReturnMessage uploadFile(MultipartFile infile) {

        if (infile == null) {
            return new ReturnMessage(Constant.RET_CODE_FILE_EMPTY, Constant.RET_MSG_FILE_EMPTY);
        }
        String suffix = uploadFileService.validateFileContentType(infile.getOriginalFilename());

        if (suffix == null) {
            return new ReturnMessage(Constant.RET_CODE_FILE_TYPE_ERROR, Constant.RET_MSG_FILE_TYPE_ERROR);
        }
        try {

            File file = new File(infile.getOriginalFilename());
            FileUtils.copyInputStreamToFile(infile.getInputStream(),file);
            Set<String> content = this.uploadFileService.uploadFile(file);

            return new ReturnMessage(Constant.RET_CODE_SUCCESS, Constant.RET_MSG_SUCCESS, content);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnMessage(Constant.RET_CODE_FILE_READ_FAIL, Constant.RET_MSG_FILE_READ_FAIL);
        }
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ReturnMessage  getFileDirList() {

        Set<String> dirs = this.uploadFileService.getAllDir();

        return new ReturnMessage(Constant.RET_CODE_SUCCESS, Constant.RET_MSG_SUCCESS,dirs);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ReturnMessage hello() {
        return new ReturnMessage(Constant.RET_CODE_SUCCESS, Constant.RET_MSG_SUCCESS);
    }
}
