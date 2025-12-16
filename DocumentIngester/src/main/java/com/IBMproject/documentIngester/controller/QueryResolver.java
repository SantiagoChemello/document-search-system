package com.IBMproject.documentIngester.controller;

import com.IBMproject.documentIngester.entity.File;
import com.IBMproject.documentIngester.service.FileService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    FileService fileService;

    public List<File> getAllFiles() {
        return fileService.getAllFiles();
    }

    public List<File> searchInDocuments(String search) {
        return fileService.searchInDocuments(search);
    }

}