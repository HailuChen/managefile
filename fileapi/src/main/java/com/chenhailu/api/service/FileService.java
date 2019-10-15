package com.chenhailu.api.service;


import java.util.Set;

public interface FileService {

    Set<String> getFileDir();

    Long saveDir(String dir);


}
