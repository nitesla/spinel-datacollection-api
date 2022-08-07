package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.BankDto;
import com.sabi.datacollection.core.dto.response.*;
import com.sabi.datacollection.service.services.BackOfficeDashboardService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.APP_CONTENT+"backoffice")
public class BackOfficeController {

    @Autowired
    private BackOfficeDashboardService service;

    @PostMapping("")
    public ResponseEntity<Response> populateBackOfficeInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        BackOfficeResponseDto response = service.populateBackOfficeInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("client")
    public ResponseEntity<Response> populateClientBackOfficeInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        ClientDashboradResponseDto response = service.populateClientBackOfficeInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("project")
    public ResponseEntity<Response> populateProjectBackOfficeInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        ProjectDashboardResponse response = service.populateProjectBackOfficeInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("role")
    public ResponseEntity<Response> populateRoleBackOfficeInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleDashboardResponseDto response = service.populateRoleBackOfficeInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("user")
    public ResponseEntity<Response> populateBackOfficeUserInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        BackOfficeUserResponseDto response = service.populateBackOfficeUserInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("auditTrail")
    public ResponseEntity<Response> populateBackOfficeAuditTrailInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        BackOfficeAuditTrailResponseDto response = service.populateBackOfficeAuditTrailInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("location")
    public ResponseEntity<Response> populateBackOfficeLocationInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        BackOfficeLocationResponseDto response = service.populateBackOfficeLocationInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("enumerator")
    public ResponseEntity<Response> populateBackOfficeEnumeratorInfo(){
        HttpStatus httpCode ;
        Response resp = new Response();
        BackOfficeEnumeratorResponseDto response = service.populateBackOfficeEnumeratorInfo();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }
}
