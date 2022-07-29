package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.ProjectRoleDto;
import com.sabi.datacollection.core.models.ProjectRole;
import com.sabi.datacollection.service.services.ProjectRoleService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.APP_CONTENT+"projectrole")
public class ProjectRoleController {
    private final ProjectRoleService service;

    @PostMapping("")
    public ResponseEntity<Response> createProjectRole(@RequestBody ProjectRoleDto request) {
        Response response = new Response();
        response.setData(service.createProjectRole(request));
        response.setCode(CustomResponseCode.CREATED);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectRole(@RequestBody ProjectRoleDto request) {
        Response response = new Response();
        response.setDescription("Update Successful");
        response.setData(service.updateProjectRole(request));
        response.setCode(CustomResponseCode.SUCCESS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectRole(@PathVariable Long id) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(service.findProjectRoleById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectRole(@RequestParam(value = "name",required = false)String name,
                                               @RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectRole> projectRoles = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectRoles);
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
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(service.getAll(isActive));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
