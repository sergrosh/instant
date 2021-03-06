package com.instant.component;

import com.instant.persistence.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author sroshchupkin
 */

@Component
public class FileComponent {

    @Autowired
    ServletContext servletContext;

    public void saveFileToLocalDisk(MultipartFile multipartFile) throws IOException {
        String outputFileName = getOutputFilename(multipartFile);
        //TODO:test for folder creation
//        String outputFileName = getOutputFilename(multipartFile).replace("$1",File.separator);
        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
    }

    public void deleteFileFromLocalDisc(String name) throws IOException {
        Path filePath = Paths.get(getDestinationLocation() + name);
        Files.delete(filePath);
    }

    public String getOutputFilename(MultipartFile multipartFile) {
        return getDestinationLocation() + multipartFile.getOriginalFilename();
    }

    public String getDestinationLocation() {
        return servletContext.getRealPath(File.separator);
    }

    public UploadedFile getUploadedFileInfo(MultipartFile multipartFile) throws IOException {
        UploadedFile fileInfo = new UploadedFile();
        fileInfo.setName(multipartFile.getOriginalFilename());
        fileInfo.setSize(multipartFile.getSize());
        fileInfo.setType(multipartFile.getContentType());
        fileInfo.setLocation(getDestinationLocation());
        return fileInfo;
    }
}
