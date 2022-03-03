package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.ProjectDto;
import com.sabi.datacollection.core.dto.response.ProjectResponseDto;
import com.sabi.datacollection.core.models.Project;
import com.sabi.datacollection.service.services.ProjectService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"project")
public class ProjectController {

    private final ProjectService service;


    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProject(@RequestBody ProjectDto request) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.createProject(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProject(@RequestBody ProjectDto request) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.updateProject(request);
        response.setDescription("Update Successful");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProject(@PathVariable Long id) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.findProjectById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjects(@RequestParam(value = "name",required = false)String name,
                                                @RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<Project> projectPage = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectPage);
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
        List<Project> projectPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
