package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.ProjectEnumeratorRequestDto;
import com.sabi.datacollection.service.services.ProjectEnumeratorService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sabi.framework.utils.Constants;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectenumerator")
public class ProjectEnumeratorController {

    private final ProjectEnumeratorService projectEnumeratorService;

    public ProjectEnumeratorController(ProjectEnumeratorService projectEnumeratorService) {
        this.projectEnumeratorService = projectEnumeratorService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectEnumerator(@RequestBody ProjectEnumeratorRequestDto projectEnumeratorRequestDto){
        Response response = new Response(CustomResponseCode.SUCCESS,"Successful");
        response.setData(projectEnumeratorService.createProjectEnumerator(projectEnumeratorRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<Response> createProjectEnumeratorsInBulk(@RequestBody List<ProjectEnumeratorRequestDto> projectEnumeratorRequestDtoList){
        Response response = new Response(CustomResponseCode.SUCCESS,"Successful");
        response.setData(projectEnumeratorService.createsProjectEnumeratorsInBulk(projectEnumeratorRequestDtoList));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("")
    public ResponseEntity<Response> updateProjectEnumerator(@RequestBody ProjectEnumeratorRequestDto projectEnumeratorRequestDto){
        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
        response.setData(projectEnumeratorService.updateProjectEnumerator(projectEnumeratorRequestDto));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/bulk")
    public ResponseEntity<Response> updateProjectEnumeratorsInBulk(@RequestBody List<ProjectEnumeratorRequestDto> projectEnumeratorRequestDtoList){
        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
        response.setData(projectEnumeratorService.updatesProjectEnumeratorsInBulk(projectEnumeratorRequestDtoList));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectEnumerator(@PathVariable Long id){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectEnumeratorService.getProjectEnumerator(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectEnumerators(@RequestParam(value = "projectId", required = false)Long projectId,
                                                          @RequestParam(value = "enumeratorId", required = false)Long enumeratorId,
                                                          @RequestParam(value = "name", required = false)String name,
                                                        @RequestParam(value = "page") Integer page,
                                                        @RequestParam(value = "pageSize")Integer pageSize) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectEnumeratorService.searchAll(projectId,enumeratorId,name, PageRequest.of(page,pageSize)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByIsActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectEnumeratorService.getActiveEnumerators(isActive));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto enableDisableDto){
        projectEnumeratorService.enableDisableState(enableDisableDto);
        return new ResponseEntity<>(new Response(CustomResponseCode.SUCCESS,"Successful"), HttpStatus.OK);
    }
}
