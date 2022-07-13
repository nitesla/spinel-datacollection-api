package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.CompleteSignupRequest;
import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.EnumeratorDto;
import com.sabi.datacollection.core.dto.request.EnumeratorSignUpDto;
import com.sabi.datacollection.core.dto.response.CompleteSignUpResponse;
import com.sabi.datacollection.core.dto.response.EnumeratorActivationResponse;
import com.sabi.datacollection.core.dto.response.EnumeratorResponseDto;
import com.sabi.datacollection.core.dto.response.EnumeratorSignUpResponseDto;
import com.sabi.datacollection.core.models.Enumerator;
import com.sabi.datacollection.service.services.EnumeratorService;
import com.sabi.framework.dto.requestDto.ChangePasswordDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"enumerator")
public class EnumeratorController {

    private final EnumeratorService service;

    public EnumeratorController(EnumeratorService service) {
        this.service = service;
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
    public ResponseEntity<Response> completeSignUp(@Validated @RequestBody CompleteSignupRequest request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CompleteSignUpResponse response = service.completeSignUp(request);
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

}
