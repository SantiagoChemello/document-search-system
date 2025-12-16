package com.IBMproject.documentIngester.service;

import com.IBMproject.documentIngester.entity.File;
import com.IBMproject.documentIngester.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File saveFile(String filename, String type, String content) {

        File file = File.builder()
                .filename(filename)
                .type(type)
                .content(content)
                .build();

        return fileRepository.save(file);
    }

    public List<File> searchInDocuments(String search){

        List<File> files = fileRepository.findAll();
        List<File> filteredFiles = new ArrayList<>();

        for (File file : files) {
            if(file.getContent().toLowerCase().contains(search.toLowerCase())){
                filteredFiles.add(file);
            }
        }

        return filteredFiles;
    }

/*
    @PostConstruct
    private void datosDePrueba (){

        saveFile("archivo1", "pdf", "contenido distinto");
        saveFile("archivo2", "docx", "contenido normal");

    }
*/
}
