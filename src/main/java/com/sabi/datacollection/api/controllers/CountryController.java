package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.CountryDto;
import com.sabi.datacollection.core.dto.response.CountryResponseDto;
import com.sabi.datacollection.core.models.Country;
import com.sabi.datacollection.service.services.CountryService;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
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

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"country")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }


    /** <summary>
     * Country creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new country</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createCountry(@Validated @RequestBody CountryDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CountryResponseDto response = service.createCountry(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Country update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating countries</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateCountry(@Validated @RequestBody  CountryDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        CountryResponseDto response = service.updateCountry(request);
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
    public ResponseEntity<Response> getCountry(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        CountryResponseDto response = service.findCountry(id);
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
    public ResponseEntity<Response> getCountries(@RequestParam(value = "name",required = false)String name,
                                              @RequestParam(value = "code",required = false)String code,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Country> response = service.findAll(name,code, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a State</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableState(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "name",required = false)String name,
                                           @RequestParam(value = "code",required = false)String code){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Country> response = service.getAll(name,code);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
