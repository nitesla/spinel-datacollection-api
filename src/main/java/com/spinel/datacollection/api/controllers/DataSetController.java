package com.spinel.datacollection.api.controllers;

import com.spinel.datacollection.core.dto.request.DataSetDto;
import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.response.DataSetResponseDto;
import com.spinel.datacollection.core.models.DataSet;
import com.spinel.datacollection.service.services.DataSetService;
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
@RequestMapping(Constants.APP_CONTENT+"dataset")
public class DataSetController {

    private final DataSetService service;

    public DataSetController(DataSetService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createDataSet(@RequestBody DataSetDto request) {
        Response response = new Response();
        DataSetResponseDto dataSetResponse = service.createDataSet(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(dataSetResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateDataSet(@RequestBody DataSetDto request) {
        Response response = new Response();
        DataSetResponseDto dataSetResponse = service.updateDataSet(request);
        response.setDescription("Update Successful");
        response.setData(dataSetResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getDataSet(@PathVariable Long id) {
        Response response = new Response();
        DataSetResponseDto dataSetResponse = service.findDataSetById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(dataSetResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getDataSets(@RequestParam(value = "name",required = false)String name,
                                                @RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<DataSet> dataSetPage = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(dataSetPage);
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

    @PutMapping("/enabledisableDataSet")
    public ResponseEntity<Response> enableDisableDataSet(@RequestBody EnableDisableDto request) {
        Response response = new Response();
        service.enableDisableDataSet(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        List<DataSet> dataSetPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(dataSetPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
