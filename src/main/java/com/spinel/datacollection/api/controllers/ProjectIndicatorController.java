package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.ProjectIndicatorDto;
import com.spinel.datacollection.core.dto.response.ProjectIndicatorResponseDto;
import com.spinel.datacollection.core.models.ProjectIndicator;
import com.spinel.datacollection.service.services.ProjectIndicatorService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectIndicator")
public class ProjectIndicatorController {

    private final ProjectIndicatorService service;

    public ProjectIndicatorController(ProjectIndicatorService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectIndicator(@RequestBody ProjectIndicatorDto request) {
        Response response = new Response();
        ProjectIndicatorResponseDto projectIndicatorResponse = service.createProjectIndicator(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectIndicatorResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectIndicator(@RequestBody ProjectIndicatorDto request) {
        Response response = new Response();
        ProjectIndicatorResponseDto projectIndicatorResponse = service.updateProjectIndicator(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Update Successful");
        response.setData(projectIndicatorResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectIndicatorById(@PathVariable Long id) {
        Response response = new Response();
        ProjectIndicatorResponseDto projectIndicatorResponse = service.findProjectIndicatorById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectIndicatorResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Response> getProjectIndicatorByProjectId(@PathVariable Long projectId,
                                                                     @RequestParam(value = "page") Integer page,
                                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectIndicator> projectIndicatorPage = service.findProjectIndicatorByProjectId(projectId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectIndicatorPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/indicator/{indicatorId}")
    public ResponseEntity<Response> getProjectIndicatorByIndicatorId(@PathVariable Long indicatorId,
                                                                   @RequestParam(value = "page") Integer page,
                                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectIndicator> projectIndicatorPage = service.findProjectIndicatorByIndicatorId(indicatorId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectIndicatorPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisableDto request) {
        Response response = new Response();
        service.enableDisableState(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive,
                                                   @RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "pageSize") Integer pageSize){
        Response response = new Response();
        Page<ProjectIndicator> projectIndicatorPage = service.findAllIsActive(isActive, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectIndicatorPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> findAll(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize){
        Response response = new Response();
        Page<ProjectIndicator> projectIndicatorPage = service.findAll(PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectIndicatorPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
