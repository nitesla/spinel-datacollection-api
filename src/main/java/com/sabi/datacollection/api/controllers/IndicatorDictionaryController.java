package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.IndicatorDictionaryDto;
import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.response.IndicatorDictionaryResponseDto;
import com.sabi.datacollection.core.models.IndicatorDictionary;
import com.sabi.datacollection.service.services.IndicatorDictionaryService;
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
@RequestMapping(Constants.APP_CONTENT + "indicatordictionary")
public class IndicatorDictionaryController {


    private final IndicatorDictionaryService service;


    public IndicatorDictionaryController(IndicatorDictionaryService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createIndicatorDictionary(@RequestBody IndicatorDictionaryDto indicatorDictionaryDto) {
        Response response = new Response();
        IndicatorDictionaryResponseDto indicatorDictionaryResponse = service.createIndicatorDictionary(indicatorDictionaryDto);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(indicatorDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateIndicatorDictionary(@RequestBody IndicatorDictionaryDto indicatorDictionaryDto) {
        Response response = new Response();
        IndicatorDictionaryResponseDto indicatorDictionaryResponse = service.updateIndicatorDictionary(indicatorDictionaryDto);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Update Successful");
        response.setData(indicatorDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getIndicatorDictionary(@PathVariable Long id) {
        Response response = new Response();
        IndicatorDictionaryResponseDto indicatorDictionaryResponse = service.findIndicatorDictionaryById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(indicatorDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getIndicatorDictionaries(@RequestParam(value = "name",required = false)String name,
                                                           @RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<IndicatorDictionary> indicatorDictionaries = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(indicatorDictionaries);
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
        List<IndicatorDictionary> indicatorDictionaryList = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(indicatorDictionaryList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
