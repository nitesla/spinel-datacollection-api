package com.spinel.datacollection.api.controllers;

import com.spinel.datacollection.service.services.ProjectOwnerDashboardService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Constants.APP_CONTENT+"projectownerdashboard")
public class ProjectOwnerDashboardController {

    private final ProjectOwnerDashboardService service;


    public ProjectOwnerDashboardController(ProjectOwnerDashboardService service) {
        this.service = service;
    }

    @GetMapping("/dashboardcards")
    public ResponseEntity<Response> getDashboardCards(@RequestParam(value = "projectOwnerId")Long projectOwnerId) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Records fetched successfully");
        resp.setData(service.dashboardInfo(projectOwnerId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    @GetMapping("/submissionSummary")
    public ResponseEntity<Response> getSubmissionSummary(@RequestParam(value = "projectOwnerId")Long projectOwnerId,
                                                         @RequestParam(value = "duration")int duration,
                                                         @RequestParam(value = "dateType")String dateType) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Records fetched successfully");
        resp.setData(service.submissionSummary(projectOwnerId, duration, dateType));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}

