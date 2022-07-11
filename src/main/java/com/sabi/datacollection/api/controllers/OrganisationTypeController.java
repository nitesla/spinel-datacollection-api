package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.OrganisationTypeDto;
import com.sabi.datacollection.core.dto.response.OrganisationTypeResponseDto;
import com.sabi.datacollection.core.models.OrganisationType;
import com.sabi.datacollection.service.services.OrganisationTypeService;
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
@RequestMapping(Constants.APP_CONTENT+"organisationtype")
public class OrganisationTypeController {

    private final OrganisationTypeService service;


    public OrganisationTypeController(OrganisationTypeService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createOrganisationType(@RequestBody OrganisationTypeDto request) {
        Response response = new Response();
        OrganisationTypeResponseDto organisationTypeResponse = service.createOrganisationType(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(organisationTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateOrganisationType(@RequestBody OrganisationTypeDto request) {
        Response response = new Response();
        OrganisationTypeResponseDto organisationTypeResponse = service.updateOrganisationType(request);
        response.setDescription("Update Successful");
        response.setData(organisationTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOrganisationType(@PathVariable Long id) {
        Response response = new Response();
        OrganisationTypeResponseDto organisationTypeResponse = service.findOrganisationTypeById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(organisationTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getOrganisationTypes(@RequestParam(value = "name",required = false)String name,
                                                         @RequestParam(value = "description",required = false)String description,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<OrganisationType> organisationTypePage = service.findAll(name, description, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(organisationTypePage);
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
        List<OrganisationType> organisationTypePage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(organisationTypePage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
