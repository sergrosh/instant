package com.instant.controller;

import com.instant.component.FileComponent;
import com.instant.persistence.model.city.City;
import com.instant.persistence.model.UploadedFile;
import com.instant.persistence.model.venue.Venue;
import com.instant.persistence.repository.CityRepository;
import com.instant.persistence.repository.UploadedFileRepository;
import com.instant.persistence.repository.VenueRepository;
import com.instant.validator.VenueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sroshchupkin
 */

@Controller
public class VenueController {

    @Autowired
    FileComponent fileComponent;

    @Autowired
    VenueValidator venueValidator;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UploadedFileRepository uploadedFileRepository;

    @RequestMapping(Mappings.ITEM)
    public ModelAndView getItemById(@RequestParam("id") String id) {
        ModelAndView modelAndView;
        if (StringUtils.isEmpty(id)) {
            modelAndView = new ModelAndView(TilesDefinition.HOME);
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(TilesDefinition.ITEM);
            modelAndView.addObject("venue", venueRepository.findById(id));
        }
        return modelAndView;
    }

    @RequestMapping(value = Mappings.VENUE_SAVE, method = RequestMethod.POST)
    public ModelAndView save(Venue venue) {
        Map<String, String> errorsMap = venueValidator.isValid(venue);
        if (errorsMap.isEmpty()) {
            ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
            venueRepository.save(venue);
            if (cityRepository.findCityByName(venue.getCity()).isEmpty()) {
                City newCity = new City();
                newCity.setName(venue.getCity());
                cityRepository.save(newCity);
            }
            return view;
        } else {
            ModelAndView view = new ModelAndView(TilesDefinition.LANDING);
            return view;
        }
    }

    @RequestMapping(value = Mappings.UPLOAD_VENUE_IMAGE, method = RequestMethod.POST)
    public
    @ResponseBody List<UploadedFile> uploadFile(MultipartHttpServletRequest request,
                HttpServletResponse response) throws IOException {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        List<UploadedFile> uploadedFiles = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.values()) {
            fileComponent.saveFileToLocalDisk(multipartFile);
            UploadedFile fileInfo = fileComponent.getUploadedFileInfo(multipartFile);
            fileInfo = uploadedFileRepository.save(fileInfo);
            uploadedFiles.add(fileInfo);
        }
        return uploadedFiles;
    }

    @RequestMapping(value=Mappings.DELETE_VENUE_IMAGE, method = RequestMethod.POST)
    public
    @ResponseBody String deleteFile(HttpServletRequest request) throws IOException{
        String fileName=request.getParameter("name");
        String name=fileName.split("\\.")[0];
        fileComponent.deleteFileFromLocalDisc(fileName);
        if(uploadedFileRepository.findFileByName(name).size()>0)
            uploadedFileRepository.deleteByName(name);
        return name;
    }
}
