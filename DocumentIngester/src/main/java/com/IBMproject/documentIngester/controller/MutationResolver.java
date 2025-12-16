package com.IBMproject.documentIngester.controller;

import com.IBMproject.documentIngester.entity.File;
import com.IBMproject.documentIngester.service.FileService;
import graphql.kickstart.tools.GraphQLMutationResolver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@Slf4j
@Service
class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    FileService fileService;

    public File upload(Part file) throws IOException {

        String filename = file.getSubmittedFileName();
        String type = file.getContentType();
        byte[] fileBytes = file.getInputStream().readAllBytes();
        String content = null;

        if ("application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(type)) {

            try (XWPFDocument docxDocument = new XWPFDocument(new ByteArrayInputStream(fileBytes))) {
                content = extractTextFromDocx(docxDocument);
                type = "DOCX";
            }
        } else if ("application/pdf".equals(type)) {

            try (PDDocument pdfDocument = PDDocument.load(new ByteArrayInputStream(fileBytes))) {
                content = extractTextFromPdf(pdfDocument);
                type = "PDF";
            }
        } else {
            throw new IllegalArgumentException("Invalid file type: Try uploading PDF or DOCX");
    }

        return fileService.saveFile(filename, type, content);
    }

    private String extractTextFromDocx(XWPFDocument docxDocument) throws IOException {
        StringBuilder text = new StringBuilder();
        docxDocument.getParagraphs().forEach(paragraph -> text.append(paragraph.getText()).append("\n"));
        return text.toString();
    }

    private String extractTextFromPdf(PDDocument pdfDocument) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        return pdfStripper.getText(pdfDocument);
    }
}
