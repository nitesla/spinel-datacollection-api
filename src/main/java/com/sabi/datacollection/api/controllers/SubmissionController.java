package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.SubmissionDto;
import com.sabi.datacollection.core.dto.response.SubmissionResponseDto;
import com.sabi.datacollection.core.enums.Status;
import com.sabi.datacollection.core.models.Submission;
import com.sabi.datacollection.service.services.SubmissionService;
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
@RequestMapping(Constants.APP_CONTENT+"submission")
public class SubmissionController {


    private final SubmissionService service;

    public SubmissionController(SubmissionService service) {
        this.service = service;
    }


    /** <summary>
     * Submission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new submissions</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createSubmission(@Validated @RequestBody SubmissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SubmissionResponseDto response = service.createSubmission(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Submission update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating submissions</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateSubmission(@Validated @RequestBody  SubmissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        SubmissionResponseDto response = service.updateSubmission(request);
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
    public ResponseEntity<Response> getSubmission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        SubmissionResponseDto response = service.findSubmission(id);
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
    public ResponseEntity<Response> getSubmissions(@RequestParam(value = "projectId",required = false)Long projectId,
                                                   @RequestParam(value = "formId",required = false)Long formId,
                                                   @RequestParam(value = "status",required = false)Status status,
                                                   @RequestParam(value = "enumeratorId",required = false) Long enumeratorId,
                                                   @RequestParam(value = "page") int page,
                                                   @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Submission> response = service.findAll(projectId,formId, status, enumeratorId,  PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Submission</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableSubmission(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Submission> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/getSubmissionGraph")
    public ResponseEntity<Response> getSubmissionsGraph(@RequestParam(value = "duration")int duration,
                                                      @RequestParam(value = "dateType")String dateType) {
        Response resp = new Response();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Records fetched successfully");
        resp.setData(service.getSubmissions(duration, dateType));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }


}
