package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.DataCollectionUserRequestDto;
import com.spinel.datacollection.core.dto.response.DataCollectionUserResponseDto;
import com.spinel.datacollection.service.services.DataCollectionUserService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(Constants.APP_CONTENT+"dataCollectionUser")
public class DataCollectionUSerController {

    @Autowired
    private DataCollectionUserService service;

    @PostMapping("")
    public ResponseEntity<Response> createDataCoUser(@Validated @RequestBody DataCollectionUserRequestDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DataCollectionUserResponseDto response = service.createDataUser(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }
}
