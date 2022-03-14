package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.response.ProjectOwnerUserResponseDto;
import com.sabi.datacollection.core.models.ProjectOwnerUser;
import com.sabi.datacollection.service.services.ProjectOwnerUserService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectowneruser")
public class ProjectOwnerUserController {

    private final ProjectOwnerUserService service;

    public ProjectOwnerUserController(ProjectOwnerUserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectOwnerUserById(@PathVariable Long id) {
        Response response = new Response();
        ProjectOwnerUserResponseDto projectOwnerUserResponse = service.findProjectOwnerUserById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerUserResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Response> getProjectOwnerUserByUserId(@PathVariable Long userId,
                                                                   @RequestParam(value = "page") Integer page,
                                                                   @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectOwnerUser> projectOwnerUserPage = service.findProjectOwnerUserByUserId(userId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerUserPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projectOwner/{projectOwnerId}")
    public ResponseEntity<Response> getProjectOwnerUserByOwnerId(@PathVariable Long projectOwnerId,
                                                                     @RequestParam(value = "page") Integer page,
                                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectOwnerUser> projectOwnerUserPage = service.findProjectOwnerUserByProjectOwnerId(projectOwnerId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerUserPage);
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

}
