package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.PricingConfigurationDto;
import com.spinel.datacollection.core.dto.response.PricingConfigurationResponseDto;
import com.spinel.datacollection.core.models.PricingConfiguration;
import com.spinel.datacollection.service.services.PricingConfigurationService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"pricingconfiguration")
public class PricingConfigurationController {

    private final PricingConfigurationService service;

    public PricingConfigurationController(PricingConfigurationService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createPricingConfiguration(@RequestBody PricingConfigurationDto request) {
        Response response = new Response();
        PricingConfigurationResponseDto pricingConfigurationResponse = service.createPricingConfiguration(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(pricingConfigurationResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updatePricingConfiguration(@RequestBody PricingConfigurationDto request) {
        Response response = new Response();
        PricingConfigurationResponseDto pricingConfigurationResponse = service.updatePricingConfiguration(request);
        response.setDescription("Update Successful");
        response.setData(pricingConfigurationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPricingConfiguration(@PathVariable Long id) {
        Response response = new Response();
        PricingConfigurationResponseDto pricingConfigurationResponse = service.findPricingConfiguration(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(pricingConfigurationResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/dataset/{datasetId}")
    public ResponseEntity<Response> getPricingConfigurationByDataSet(@PathVariable Long datasetId,
                                                                     @RequestParam(value = "page") Integer page,
                                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<PricingConfiguration> pricingConfigurationResponsePage = service.findPricingConfigurationByDataSetId(datasetId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(pricingConfigurationResponsePage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<Response> getAllPricingConfiguration(@RequestParam(value = "page") Integer page,
                                                                     @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<PricingConfiguration> pricingConfigurationResponsePage = service.findAll(PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(pricingConfigurationResponsePage);
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
        List<PricingConfiguration> pricingConfigurationPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(pricingConfigurationPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
