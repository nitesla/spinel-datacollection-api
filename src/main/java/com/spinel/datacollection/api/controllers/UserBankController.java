package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.UserBankRequestDto;
import com.sabi.datacollection.core.dto.response.UserBankResponseDto;
import com.sabi.datacollection.core.models.UserBank;
import com.sabi.datacollection.service.services.UserBankService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"userBank")
public class UserBankController {
    private final UserBankService service;

    public UserBankController(UserBankService service) {
        this.service = service;
    }


    /** <summary>
     * UserBank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new UserBanks</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createUserBank(@Validated @RequestBody UserBankRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserBankResponseDto response = service.createUserBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * UserBank update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating UserBanks</remarks>
     */

    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateUserBank(@Validated @RequestBody  UserBankRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserBankResponseDto response = service.updateUserBank(request);
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
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getUserBank(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        UserBankResponseDto response = service.findUserBank(id);
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

    @GetMapping("/page")
    public ResponseEntity<Response> getUserBanks(@RequestParam(value = "bankId",required = false)Long bankId,
                                                    @RequestParam(value = "userId",required = false) Long userId,
                                                    @RequestParam(value = "accountNumber",required = false)String accountNumber,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<UserBank> response = service.findAll(bankId, userId, accountNumber,PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a UserBank</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableUserBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<UserBank> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
