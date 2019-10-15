package com.chenhailu.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.chenhailu.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
@Component
public class FileServiceImpl implements FileService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Set<String> getFileDir() {
        Set<String> dirs = this.redisTemplate.opsForSet().members("fileUrl");
        return dirs;
    }

    @Override
    public Long saveDir(String dir) {
         Long ind =  this.redisTemplate.opsForSet().add("fileUrl",dir);
         return  ind;
    }
}
