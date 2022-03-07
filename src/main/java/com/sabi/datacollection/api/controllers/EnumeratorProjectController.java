package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.EnumeratorProjectDto;
import com.sabi.datacollection.core.dto.response.EnumeratorProjectResponseDto;
import com.sabi.datacollection.core.enums.Status;
import com.sabi.datacollection.core.models.EnumeratorProject;
import com.sabi.datacollection.service.services.EnumeratorProjectService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"enumeratorproject")
public class EnumeratorProjectController {

    private final EnumeratorProjectService service;

    public EnumeratorProjectController(EnumeratorProjectService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createEnumeratorProject(@RequestBody EnumeratorProjectDto request) {
        Response response = new Response();
        EnumeratorProjectResponseDto enumeratorProjectResponse = service.createEnumeratorProject(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(enumeratorProjectResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateEnumeratorProject(@RequestBody EnumeratorProjectDto request) {
        Response response = new Response();
        EnumeratorProjectResponseDto enumeratorProjectResponse = service.updateEnumeratorProject(request);
        response.setDescription("Update Successful");
        response.setData(enumeratorProjectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEnumeratorProject(@PathVariable Long id) {
        Response response = new Response();
        EnumeratorProjectResponseDto enumeratorProjectResponse = service.findEnumeratorProjectById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(enumeratorProjectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectCategories(@RequestParam(value = "projectId",required = false)Long projectId,
                                                         @RequestParam(value = "enumeratorId",required = false)Long enumeratorId,
                                                         @RequestParam(value = "assignedDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime assignedDate,
                                                         @RequestParam(value = "completionDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime completionDate,
                                                         @RequestParam(value = "status",required = false) Status status,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<EnumeratorProject> enumeratorProjectPage = service.findAll(projectId, enumeratorId, assignedDate, completionDate, status, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(enumeratorProjectPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto request) {
        Response response = new Response();
        service.enableDisableState(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        List<EnumeratorProject> enumeratorProjectPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(enumeratorProjectPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
