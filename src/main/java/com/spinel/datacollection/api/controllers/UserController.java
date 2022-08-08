package com.spinel.datacollection.api.controllers;



import com.spinel.framework.dto.requestDto.*;
import com.spinel.framework.dto.responseDto.ActivateUserResponse;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.dto.responseDto.UserResponse;
import com.spinel.framework.models.User;
import com.spinel.framework.service.UserService;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"user")
public class UserController {

    private  static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService service;
    private final ModelMapper mapper;

    public UserController(UserService service,ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    /** <summary>
     * User creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new user</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createUser(@Validated @RequestBody UserDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserResponse response = service.createUser(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * User update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating user</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateUser(@Validated @RequestBody  UserDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserResponse response = service.updateUser(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getUser(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserResponse response = service.findUser(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> getUsers(@RequestParam(value = "firstName",required = false)String firstName,
                                             @RequestParam(value = "lastName",required = false)String lastName,
                                             @RequestParam(value = "phone",required = false)String phone,
                                             @RequestParam(value = "role",required = false)String role,
                                             @RequestParam(value = "roleId",required = false)Long roleId,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                             @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                             @RequestParam(value = "email",required = false)String email,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "pageSize") int pageSize){

            HttpStatus httpCode ;
        Response resp = new Response();
        Page<User> response = service.findAll(firstName,lastName,phone,role,roleId,isActive,startDate,endDate,email, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a user</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableUser(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Change password
     * </summary>
     * <remarks>this endpoint is responsible for changing password</remarks>
     */

    @PutMapping("/changepassword")
    public ResponseEntity<Response> changePassword(@Validated @RequestBody ChangePasswordDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.changeUserPassword(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Password changed successfully");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/unlock")
    public ResponseEntity<Response> unlockAccount(@Validated @RequestBody UnlockAccountRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.unlockAccounts(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("User account unlocked successfully");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/forgetpassword")
    public ResponseEntity<Response> forgetPassword(@Validated @RequestBody ForgetPasswordDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.forgetPassword(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/activateUser")
    public ResponseEntity<Response> activateUser(@Validated @RequestBody ActivateUserAccountDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        ActivateUserResponse response = service.activateUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/changepin")
    public ResponseEntity<Response> transactionPin(@Validated @RequestBody SetTransactionPin request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.setPin(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @PutMapping("/pinotp")
    public ResponseEntity<Response> transactionPinOtp(@Validated @RequestBody CreateTransactionPinDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.resetPinOTP(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @PutMapping("/transactionpin")
    public ResponseEntity<Response> changeTransactionPin(@Validated @RequestBody CreateTransactionPinDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.resetPin(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<User> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("/passwordactivation")
    public ResponseEntity<Response> passwordActivation(@Validated @RequestBody PasswordActivationRequest request) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(service.userPasswordActivation(request));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
