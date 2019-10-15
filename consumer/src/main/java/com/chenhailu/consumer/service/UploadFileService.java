package com.chenhailu.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chenhailu.api.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UploadFileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileService.class);

    @Value("${file.uploadFolder}")
    private String outputdirPrefix ;

    @Value("${file.url}")
    private String fileUrl ;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://127.0.0.1:20880",check=false)
    private FileService fileService;

    public  String validateFileContentType(String fileName) {
        LOGGER.info(fileName);
        if (fileName.contains(".")) {
            String fileType = fileName.split("\\.")[1];

            if (fileType != null && fileType.toLowerCase().matches("word|pdf|xls|txt|jpg|png|mp4")) {
                return fileType;
            }
        }

        return null;
    }
    public Set<String> uploadFile(File file) throws IOException {

        String name  = file.getName();
        StringBuilder outputDir = new StringBuilder(outputdirPrefix);
        outputDir.append(name);

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(outputDir.toString());

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        while (true) {
            buffer.clear();
            int i = inChannel.read(buffer);

            if (i == -1) {
                break;
            }
            buffer.flip();

            outChannel.write(buffer);
        }
        fis.close();
        fos.close();

        //需要校验保存是否成功
        Long ind = this.fileService.saveDir(fileUrl+name);

        Set<String>  dirs = this.getAllDir();
        return dirs;
    }


    public Set<String> getAllDir() {
       return  this.fileService.getFileDir();
    }

}
