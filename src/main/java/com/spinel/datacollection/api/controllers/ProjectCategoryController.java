package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.ProjectCategoryDto;
import com.spinel.datacollection.core.dto.response.ProjectCategoryResponseDto;
import com.spinel.datacollection.core.models.ProjectCategory;
import com.spinel.datacollection.service.services.ProjectCategoryService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectcategory")
public class ProjectCategoryController {

    private final ProjectCategoryService service;

    public ProjectCategoryController(ProjectCategoryService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createProjectCategory(@RequestBody ProjectCategoryDto request) {
        Response response = new Response();
        ProjectCategoryResponseDto projectCategoryResponse = service.createProjectCategory(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectCategoryResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectCategory(@RequestBody ProjectCategoryDto request) {
        Response response = new Response();
        ProjectCategoryResponseDto projectCategoryResponse = service.updateProjectCategory(request);
        response.setDescription("Update Successful");
        response.setData(projectCategoryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectCategory(@PathVariable Long id) {
        Response response = new Response();
        ProjectCategoryResponseDto projectCategoryResponse = service.findProjectCategoryById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectCategoryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectCategories(@RequestParam(value = "name",required = false)String name,
                                                         @RequestParam(value = "description",required = false)String description,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectCategory> projectCategoryPage = service.findAll(name, description, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectCategoryPage);
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
        List<ProjectCategory> projectCategoryPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectCategoryPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
