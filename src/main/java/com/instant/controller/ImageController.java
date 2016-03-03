package com.instant.controller;

import com.instant.component.FileComponent;
import com.instant.persistence.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sroshchupkin
 */

@Controller
public class ImageController {

    @Autowired
    FileComponent fileComponent;

    @RequestMapping(value = Mappings.UPLOAD_VENUE_IMAGE, method = RequestMethod.POST)
    public
    @ResponseBody
    List<UploadedFile> uploadFile(MultipartHttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        List<UploadedFile> uploadedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.values()) {
            fileComponent.saveFileToLocalDisk(multipartFile);
            UploadedFile fileInfo = fileComponent.getUploadedFileInfo(multipartFile);
            //fileInfo = uploadedFileRepository.save(fileInfo);
            uploadedFiles.add(fileInfo);
        }
        return uploadedFiles;
    }

    @RequestMapping(value = Mappings.DELETE_VENUE_IMAGE, method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteFile(HttpServletRequest request) throws IOException {
        String fileName = request.getParameter("name");
        String name = fileName.split("\\.")[0];
        fileComponent.deleteFileFromLocalDisc(fileName);
//        if(uploadedFileRepository.findFileByName(name).size()>0)
//            uploadedFileRepository.deleteByName(name);
        return name;
    }
}
