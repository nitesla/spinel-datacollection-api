package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.CompleteSignupRequest;
import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.ProjectOwnerDto;
import com.sabi.datacollection.core.dto.request.ProjectOwnerSignUpDto;
import com.sabi.datacollection.core.dto.response.CompleteSignUpResponse;
import com.sabi.datacollection.core.dto.response.ProjectOwnerActivationResponse;
import com.sabi.datacollection.core.dto.response.ProjectOwnerResponseDto;
import com.sabi.datacollection.core.dto.response.ProjectOwnerSignUpResponseDto;
import com.sabi.datacollection.core.models.ProjectOwner;
import com.sabi.datacollection.service.services.ProjectOwnerService;
import com.sabi.framework.dto.requestDto.ChangePasswordDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectowner")
public class ProjectOwnerController {

    private final ProjectOwnerService service;

    public ProjectOwnerController(ProjectOwnerService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> projectOwnerSignUp(@RequestBody ProjectOwnerSignUpDto request, HttpServletRequest httpServletRequest) {
        Response response = new Response();
        ProjectOwnerSignUpResponseDto projectOwnerSignUpResponse = service.projectOwnerSignUp(request, httpServletRequest);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectOwnerSignUpResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/completesignup")
    public ResponseEntity<Response> completeSignUp(@RequestBody CompleteSignupRequest request) {
        Response response = new Response();
        CompleteSignUpResponse completeSignUpResponse = service.completeSignUp(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(completeSignUpResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/passwordactivation")
    public ResponseEntity<Response> enumeratorPasswordActivation(@RequestBody ChangePasswordDto request) {
        Response response = new Response();
        ProjectOwnerActivationResponse projectOwnerActivationResponse = service.enumeratorPasswordActivation(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Password changed successfully");
        response.setData(projectOwnerActivationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectOwner(@RequestBody ProjectOwnerDto request) {
        Response response = new Response();
        ProjectOwnerResponseDto projectOwnerResponse = service.createProjectOwner(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectOwnerResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectOwner(@RequestBody ProjectOwnerDto request, HttpServletRequest httpServletRequest) {
        Response response = new Response();
        ProjectOwnerResponseDto projectOwnerResponse = service.updateProjectOwner(request, httpServletRequest);
        response.setDescription("Update Successful");
        response.setData(projectOwnerResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectOwner(@PathVariable Long id) {
        Response response = new Response();
        ProjectOwnerResponseDto projectOwnerResponse = service.findProjectOwnerById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectOwners(@RequestParam(value = "firstname",required = false)String firstname,
                                                     @RequestParam(value = "lastname",required = false)String lastname,
                                                     @RequestParam(value = "email",required = false)String email,
                                                     @RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectOwner> projectOwnerPage = service.findAll(firstname, lastname, email, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectOwnerPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto request, HttpServletRequest httpServletRequest) {
        Response response = new Response();
        service.enableDisable(request, httpServletRequest);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        List<ProjectOwner> projectOwnerPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectOwnerPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
