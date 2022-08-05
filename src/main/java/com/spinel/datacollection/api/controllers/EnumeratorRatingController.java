package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.EnumeratorRatingDto;
import com.spinel.datacollection.core.dto.response.EnumeratorRatingResponseDto;
import com.spinel.datacollection.core.models.EnumeratorRating;
import com.spinel.datacollection.service.services.EnumeratorRatingService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"enumeratorrating")
public class EnumeratorRatingController {

    private final EnumeratorRatingService service;

    public EnumeratorRatingController(EnumeratorRatingService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createEnumeratorRating(@RequestBody EnumeratorRatingDto request) {
        Response response = new Response();
        EnumeratorRatingResponseDto enumeratorRatingResponse = service.createEnumeratorRating(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(enumeratorRatingResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateEnumeratorRating(@RequestBody EnumeratorRatingDto request) {
        Response response = new Response();
        EnumeratorRatingResponseDto enumeratorRatingResponse = service.updateEnumeratorRating(request);
        response.setDescription("Update Successful");
        response.setData(enumeratorRatingResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getEnumeratorRating(@PathVariable Long id) {
        Response response = new Response();
        EnumeratorRatingResponseDto enumeratorRatingResponse = service.findEnumeratorRatingById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(enumeratorRatingResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectCategories(@RequestParam(value = "enumeratorProjectId",required = false)Long enumeratorProjectId,
                                                         @RequestParam(value = "rating",required = false)Integer rating,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<EnumeratorRating> enumeratorRatingPage = service.findAll(enumeratorProjectId, rating, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(enumeratorRatingPage);
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
        List<EnumeratorRating> enumeratorRatingPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(enumeratorRatingPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
