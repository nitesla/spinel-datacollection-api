package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.BankDto;
import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.response.BankResponseDto;
import com.spinel.datacollection.core.models.Bank;
import com.spinel.datacollection.service.services.BankService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.service.WhatsAppService;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"bank")
public class BankController {


    private final BankService service;

    public BankController(BankService service, WhatsAppService whatsAppService) {
        this.service = service;
    }


    /** <summary>
     * Bank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new bank</remarks>
     */
    @PostMapping("")
    public ResponseEntity<Response> createBank(@Validated @RequestBody BankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        BankResponseDto response = service.createBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Bank update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating bank</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateBank(@Validated @RequestBody  BankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        BankResponseDto response = service.updateBank(request);
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
    public ResponseEntity<Response> getBank(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        BankResponseDto response = service.findBank(id);
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
    public ResponseEntity<Response> getBanks(@RequestParam(value = "name",required = false)String name,
                                              @RequestParam(value = "code",required = false)String code,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Bank> response = service.findAll(name,code, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a bank</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Bank> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
