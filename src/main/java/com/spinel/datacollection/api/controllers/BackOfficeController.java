package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.response.*;
import com.spinel.datacollection.service.services.BackOfficeDashboardService;
import com.spinel.framework.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.APP_CONTENT+"backoffice")
public class BackOfficeController {

    @Autowired
    private BackOfficeDashboardService service;

    @PostMapping("")
    public BackOfficeResponseDto populateBackOfficeInfo(){
        BackOfficeResponseDto responseDto = service.populateBackOfficeInfo();
        return responseDto;
    }

    @PostMapping("client")
    public ClientDashboradResponseDto populateClientBackOfficeInfo(){
        ClientDashboradResponseDto responseDto = service.populateClientBackOfficeInfo();
        return responseDto;
    }

    @PostMapping("project")
    public ProjectDashboardResponse populateProjectBackOfficeInfo(){
        ProjectDashboardResponse responseDto = service.populateProjectBackOfficeInfo();
        return responseDto;
    }

    @PostMapping("role")
    public RoleDashboardResponseDto populateRoleBackOfficeInfo(){
        RoleDashboardResponseDto responseDto = service.populateRoleBackOfficeInfo();
        return responseDto;
    }

    @PostMapping("user")
    public BackOfficeUserResponseDto populateBackOfficeUserInfo(){
        BackOfficeUserResponseDto responseDto = service.populateBackOfficeUserInfo();
        return responseDto;
    }

    @PostMapping("auditTrail")
    public BackOfficeAuditTrailResponseDto populateBackOfficeAuditTrailInfo(){
        BackOfficeAuditTrailResponseDto responseDto = service.populateBackOfficeAuditTrailInfo();
        return responseDto;
    }

    @PostMapping("location")
    public BackOfficeLocationResponseDto populateBackOfficeLocationInfo(){
        BackOfficeLocationResponseDto responseDto = service.populateBackOfficeLocationInfo();
        return responseDto;
    }

    @PostMapping("enumerator")
    public BackOfficeEnumeratorResponseDto populateBackOfficeEnumeratorInfo(){
        BackOfficeEnumeratorResponseDto responseDto = service.populateBackOfficeEnumeratorInfo();
        return responseDto;
    }
}
