package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.EnumeratorProjectLocationDto;
import com.sabi.datacollection.core.dto.response.EnumeratorProjectLocResponseDto;
import com.sabi.datacollection.core.models.EnumeratorProjectLocation;
import com.sabi.datacollection.service.services.EnumeratorProjectLocService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"enumeratorprojectloc")
public class EnumeratorProjectLocController {

    private final EnumeratorProjectLocService service;

    public EnumeratorProjectLocController(EnumeratorProjectLocService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createEnumeratorProjectLoc(@RequestBody EnumeratorProjectLocationDto request) {
        Response response = new Response();
        EnumeratorProjectLocResponseDto enumeratorProjectLocResponse = service.createEnumeratorProjectLocation(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(enumeratorProjectLocResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateEnumeratorProjectLoc(@RequestBody EnumeratorProjectLocationDto request) {
        Response response = new Response();
        EnumeratorProjectLocResponseDto enumeratorProjectLocResponse = service.updateEnumeratorProjectLocation(request);
        response.setDescription("Update Successful");
        response.setData(enumeratorProjectLocResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEnumeratorProjectLoc(@PathVariable Long id) {
        Response response = new Response();
        EnumeratorProjectLocResponseDto enumeratorProjectLocResponse = service.findEnumeratorProjectLocationById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(enumeratorProjectLocResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectLocCategories(@RequestParam(value = "enumeratorProjectId",required = false)Long enumeratorProjectId,
                                                         @RequestParam(value = "projectLocationId",required = false)Long projectLocationId,
                                                         @RequestParam(value = "collectedRecord",required = false) Integer collectedRecord,
                                                         @RequestParam(value = "expectedRecord",required = false) Integer expectedRecord,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<EnumeratorProjectLocation> enumeratorProjectLocPage = service.findAll(enumeratorProjectId, projectLocationId, collectedRecord, expectedRecord, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(enumeratorProjectLocPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto request) {
        Response response = new Response();
        service.enableDisableState(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        List<EnumeratorProjectLocation> enumeratorProjectLocPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(enumeratorProjectLocPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
