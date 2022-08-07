package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.SubmissionCommentDto;
import com.spinel.datacollection.core.dto.response.SubmissionCommentResponseDto;
import com.spinel.datacollection.core.models.SubmissionComment;
import com.spinel.datacollection.service.services.SubmissionCommentService;
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
@RequestMapping(Constants.APP_CONTENT+"submissioncomment")
public class SubmissionCommentController {

    private final SubmissionCommentService service;

    public SubmissionCommentController(SubmissionCommentService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createSubmissionComment(@RequestBody SubmissionCommentDto request) {
        Response response = new Response();
        SubmissionCommentResponseDto submissionCommentResponse = service.createSubmissionComment(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(submissionCommentResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateSubmissionComment(@RequestBody SubmissionCommentDto request) {
        Response response = new Response();
        SubmissionCommentResponseDto submissionCommentResponse = service.updateSubmissionComment(request);
        response.setDescription("Update Successful");
        response.setData(submissionCommentResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getSubmissionComment(@PathVariable Long id) {
        Response response = new Response();
        SubmissionCommentResponseDto submissionCommentResponse = service.findSubmissionCommentById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(submissionCommentResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjectCategories(@RequestParam(value = "submissionId",required = false)Long submissionId,
                                                         @RequestParam(value = "commentId",required = false)Long commentId,
                                                         @RequestParam(value = "additionalInfo",required = false)String additionalInfo,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<SubmissionComment> submissionCommentPage = service.findAll(submissionId, commentId, additionalInfo, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(submissionCommentPage);
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
        List<SubmissionComment> submissionCommentPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(submissionCommentPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
