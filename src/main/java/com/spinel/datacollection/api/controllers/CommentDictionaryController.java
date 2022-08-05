package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.CommentDictionaryDto;
import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.response.CommentDictionaryResponseDto;
import com.spinel.datacollection.core.models.CommentDictionary;
import com.spinel.datacollection.service.services.CommentDictionaryService;
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
@RequestMapping(Constants.APP_CONTENT+"commentdictionary")
public class CommentDictionaryController {

    private final CommentDictionaryService service;


    public CommentDictionaryController(CommentDictionaryService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createCommentDictionary(@RequestBody CommentDictionaryDto commentDictionaryDto) {
        Response response = new Response();
        System.err.println(commentDictionaryDto);
        CommentDictionaryResponseDto commentDictionaryResponse = service.createCommentDictionary(commentDictionaryDto);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(commentDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateCommentDictionary(@RequestBody CommentDictionaryDto commentDictionaryDto) {
        Response response = new Response();
        CommentDictionaryResponseDto commentDictionaryResponse = service.updateCommentDictionary(commentDictionaryDto);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Update Successful");
        response.setData(commentDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCommentDictionary(@PathVariable Long id) {
        Response response = new Response();
        CommentDictionaryResponseDto commentDictionaryResponse = service.findCommentDictionaryById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(commentDictionaryResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getCommentDictionaries(@RequestParam(value = "name",required = false)String name,
                                                           @RequestParam(value = "page") Integer page,
                                                           @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<CommentDictionary> commentDictionaries = service.findAll(name, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(commentDictionaries);
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
        List<CommentDictionary> commentDictionaryList = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(commentDictionaryList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
