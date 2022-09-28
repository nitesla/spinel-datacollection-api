package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.*;
import com.spinel.datacollection.core.dto.response.CompleteSignUpResponse;
import com.spinel.datacollection.core.dto.response.EnumeratorActivationResponse;
import com.spinel.datacollection.core.dto.response.EnumeratorResponseDto;
import com.spinel.datacollection.core.dto.response.EnumeratorSignUpResponseDto;
import com.spinel.datacollection.core.models.Enumerator;
import com.spinel.datacollection.service.services.EnumeratorService;
import com.spinel.framework.dto.requestDto.ActivateUserAccountDto;
import com.spinel.framework.dto.requestDto.ChangePasswordDto;
import com.spinel.framework.dto.requestDto.PasswordActivationRequest;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.service.UserService;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"enumerator")
public class EnumeratorController {

    private final EnumeratorService service;
    private final UserService userService;

    public EnumeratorController(EnumeratorService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }



    @PostMapping("/signup")
    public ResponseEntity<Response> enumeratorSignUp(@Validated @RequestBody EnumeratorSignUpDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        EnumeratorSignUpResponseDto response = service.enumeratorSignUp(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/completesignup")
    public ResponseEntity<Response> completeSignUp(@Validated @RequestBody CompleteSignupRequest request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        CompleteSignUpResponse response = service.completeSignUp(request, request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/passwordactivation")
    public ResponseEntity<Response> enumeratorPasswordActivation(@Validated @RequestBody ChangePasswordDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        EnumeratorActivationResponse response = service.enumeratorPasswordActivation(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Password changed successfully");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @Deprecated
    @PostMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> createEnumeratorProperties(@Validated @RequestBody EnumeratorDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        EnumeratorResponseDto response = service.createEnumeratorProperties(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateEnumeratorProperties(@Validated @RequestBody  EnumeratorDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        EnumeratorResponseDto response = service.updateEnumeratorProperties(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getEnumeratorProperty(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        EnumeratorResponseDto response = service.findEnumeratorAsset(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/page/isActive")
    public ResponseEntity<Response> getEnumeratorPage(@RequestParam(value = "isActive")Boolean isActive,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode;
        Response resp = new Response();
        Page<Enumerator> response = service.getAll(isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnable(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Enumerator> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/enumeratorsummary")
    public ResponseEntity<Response> enumeratorSummary(@RequestParam(value = "enumeratorId")Long enumeratorId) {
        Response resp = new Response();
        resp.setData(service.enumeratorSummary(enumeratorId));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/enumeratorsummarywithdate")
    public ResponseEntity<Response> enumeratorSummaryWithDate(@RequestParam(value = "enumeratorId")Long enumeratorId,
                                                      @RequestParam(value = "startDate")String startDate,
                                                      @RequestParam(value = "endDate", required = false)String endDate) {
        Response resp = new Response();
        resp.setData(service.enumeratorSummary(enumeratorId, startDate, endDate));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/enumeratorprojectsummary")
    public ResponseEntity<Response> enumeratorProjectSummary(@RequestParam(value = "enumeratorId")Long enumeratorId,
                                                             @RequestParam(value = "duration")int duration,
                                                             @RequestParam(value = "dateType")String dateType) {
        Response resp = new Response();
        resp.setData(service.enumeratorProjectSummary(enumeratorId, duration, dateType));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
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

    @ApiOperation(value = "Activate account with userId ", notes = "Required id is user id")
    @PostMapping("/accountactivation")
    public ResponseEntity<Response> accountActivation(@Validated @RequestBody PasswordActivationRequest request) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(userService.userPasswordActivation(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getenumeratorwithuserid")
    public ResponseEntity<Response> getEnumeratorWithUserId(@RequestParam(value = "userId")Long userId) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.getEnumeratorWithUserId(userId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getenumeratorswithstatus")
    public ResponseEntity<Response> getEnumeratorsWithVerificationStatus(@RequestParam(value = "verificationStatus")String verificationStatus) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.getEnumeratorByVerificartionStatus(verificationStatus));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/updateEnumeratorVerificationStatus")
    public ResponseEntity<Response> updateEnumeratorVerificationStatus(@RequestParam(value = "enumeratorId")Long enumeratorId,
                                                                       @RequestParam(value = "verificationStatus")String verificationStatus) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Verification status updated successfully !");
        service.updateVerificationStatus(enumeratorId, verificationStatus);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/getenumeratorKyc/{enumeratorId}")
    public ResponseEntity<Response> getEnumeratorKyc(@PathVariable Long enumeratorId) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.getEnumeratorKYC(enumeratorId));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<Response> getForms(@Validated @RequestBody GetRequestDto request){

        HttpStatus httpCode ;
        Response resp = new Response();
        if ((request.getPage() != null || request.getPageSize() != null) && request.getFilterCriteria() == null){
            Page<Enumerator> response = service.getEntities(request);
            resp.setData(response);
        } else if (request.getPage() != null && request.getPageSize() != null) {
            Page<Enumerator> response = service.findPaginated(request);
            resp.setData(response);
        } else {
            List<Enumerator> response = service.findList(request);
            resp.setData(response);
        }
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
