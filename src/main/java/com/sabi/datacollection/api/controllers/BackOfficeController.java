package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.response.BackOfficeResponseDto;
import com.sabi.datacollection.service.services.BackOfficeDashboardService;
import com.sabi.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.APP_CONTENT+"backOffice")
public class BackOfficeController {

    @Autowired
    private BackOfficeDashboardService service;

    @PostMapping("")
    public BackOfficeResponseDto populateBackOfficeInfo(){
        BackOfficeResponseDto responseDto = service.populateBackOfficeInfo();
        return responseDto;
    }
}
