package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.SectorDto;
import com.sabi.datacollection.core.dto.response.SectorResponseDto;
import com.sabi.datacollection.core.models.Sector;
import com.sabi.datacollection.service.services.SectorService;
import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"sector")
public class SectorController {

    private final SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }



    @PostMapping("")
    public ResponseEntity<Response> createSector(@RequestBody SectorDto request) {
        Response response = new Response();
        SectorResponseDto sectorResponse = service.createSector(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(sectorResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateSector(@RequestBody SectorDto request) {
        Response response = new Response();
        SectorResponseDto sectorResponse = service.updateSector(request);
        response.setDescription("Update Successful");
        response.setData(sectorResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getSector(@PathVariable Long id) {
        Response response = new Response();
        SectorResponseDto sectorResponse = service.findSectorById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(sectorResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getSectors(@RequestParam(value = "name",required = false)String name,
                                               @RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<Sector> sectorPage = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(sectorPage);
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
        List<Sector> sectorPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(sectorPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
