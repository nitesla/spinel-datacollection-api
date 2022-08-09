package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.ProjectLocationDto;
import com.spinel.datacollection.core.dto.response.ProjectLocationResponseDto;
import com.spinel.datacollection.core.models.ProjectLocation;
import com.spinel.datacollection.service.services.ProjectLocationService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectlocation")
public class ProjectLocationController {

    private final ProjectLocationService service;

    public ProjectLocationController(ProjectLocationService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectLocation(@RequestBody ProjectLocationDto request) {
        Response response = new Response();
        ProjectLocationResponseDto projectLocationResponse = service.createProjectLocation(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectLocationResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectLocation(@RequestBody ProjectLocationDto request) {
        Response response = new Response();
        ProjectLocationResponseDto projectLocationResponse = service.updateProjectLocation(request);
        response.setDescription("Update Successful");
        response.setData(projectLocationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectLocation(@PathVariable Long id) {
        Response response = new Response();
        ProjectLocationResponseDto projectLocationResponse = service.findProjectLocationById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectLocationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<Response> getProjectLocationByLocationId(@PathVariable Long locationId,
                                                                   @RequestParam(value = "page") Integer page,
                                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectLocation> projectLocationResponse = service.findProjectLocationByLocationId(locationId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectLocationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<Response> getProjectLocationByLocationType(@RequestParam(value = "name")String location,
                                                                   @RequestParam(value = "page") Integer page,
                                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectLocation> projectLocationResponse = service.findProjectLocationByLocationType(location, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectLocationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectLocations(@RequestParam(value = "name",required = false)String name,
                                                        @RequestParam(value = "locationType",required = false)String locationType,
                                                       @RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectLocation> projectLocationPage = service.findAll(name, locationType, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectLocationPage);
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
    public ResponseEntity<Response> getAllByIsActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        List<ProjectLocation> projectLocationPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectLocationPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
