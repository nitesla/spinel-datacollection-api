package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.*;
import com.spinel.datacollection.core.dto.response.CompleteProjectOwnerSignUpResponse;
import com.spinel.datacollection.core.dto.response.ProjectOwnerActivationResponse;
import com.spinel.datacollection.core.dto.response.ProjectOwnerResponseDto;
import com.spinel.datacollection.core.dto.response.ProjectOwnerSignUpResponseDto;
import com.spinel.datacollection.core.models.ProjectOwner;
import com.spinel.datacollection.service.services.ProjectOwnerService;
import com.spinel.framework.dto.requestDto.ActivateUserAccountDto;
import com.spinel.framework.dto.requestDto.ChangePasswordDto;
import com.spinel.framework.dto.requestDto.ForgetPasswordDto;
import com.spinel.framework.dto.requestDto.PasswordActivationRequest;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<Response> completeSignUp(@RequestBody CompleteSignupProjectOwnerRequest request) {
        Response response = new Response();
        CompleteProjectOwnerSignUpResponse completeSignUpResponse = service.completeSignUp(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(completeSignUpResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/passwordactivation")
    public ResponseEntity<Response> passwordActivation(@RequestBody ChangePasswordDto request) {
        Response response = new Response();
        ProjectOwnerActivationResponse projectOwnerActivationResponse = service.passwordActivation(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Password changed successfully");
        response.setData(projectOwnerActivationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectOwner(@RequestBody ProjectOwnerDto request, HttpServletRequest httpServletRequest) {
        Response response = new Response();
        ProjectOwnerResponseDto projectOwnerResponse = service.createProjectOwner(request, httpServletRequest);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectOwnerResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectOwner(@RequestBody UpdateProjectOwnerDto request, HttpServletRequest httpServletRequest) {
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

    @PutMapping("/forgetpassword")
    public ResponseEntity<Response> forgetPassword(@Validated @RequestBody ForgetPasswordDto request){
        Response resp = new Response();
        service.forgetPassword(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getprojectownerwithuserid")
    public ResponseEntity<Response> getProjectOwnerWithUserId(@RequestParam(value = "userId")Long userId) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.findProjectOwnerByUserId(userId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getsummary")
    public ResponseEntity<Response> getProjectOwnerSummary(@RequestParam(value = "projectOwnerId")Long projectOwnerId) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.getProjectSummary(projectOwnerId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/validateOtp")
    public ResponseEntity<Response> validateOtpAndActivateUser(@Validated @RequestBody ActivateUserAccountDto request){
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(service.validateOtpAndActivateUser(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getprojectownersummarywithdate")
    public ResponseEntity<Response> getProjectOwnerSummaryWithDate(@RequestParam(value = "projectOwnerId")Long projectOwnerId,
                                                              @RequestParam(value = "startDate")String startDate,
                                                              @RequestParam(value = "endDate", required = false)String endDate) {
        Response resp = new Response();
        resp.setData(service.getProjectSummary(projectOwnerId, startDate, endDate));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ApiOperation(value = "Activate account with userId", notes = "Required id is user id")
    @PostMapping("/accountactivation")
    public ResponseEntity<Response> accountActivation(@Validated @RequestBody PasswordActivationRequest request) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Account activated successfully !");
        resp.setData(service.accountActivation(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
