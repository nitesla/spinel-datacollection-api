package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.GetRequestDto;
import com.spinel.datacollection.core.dto.request.SubmissionDto;
import com.spinel.datacollection.core.dto.response.SubmissionResponseDto;
import com.spinel.datacollection.core.enums.SubmissionStatus;
import com.spinel.datacollection.core.models.Submission;
import com.spinel.datacollection.service.services.SubmissionService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private  static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);


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
                                                   @RequestParam(value = "status",required = false) SubmissionStatus status,
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
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive,
                                                   @RequestParam(value = "projectId",required = false)Long projectId,
                                                   @RequestParam(value = "formId",required = false)Long formId,
                                                   @RequestParam(value = "enumeratorId",required = false) Long enumeratorId,
                                                   @RequestParam(value = "commentId",required = false) Long commentId,
                                                   @RequestParam(value = "deviceId",required = false) Long deviceId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Submission> response = service.getAll(isActive, projectId, formId, enumeratorId, commentId, deviceId);
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

    @PostMapping("/filter")
    public ResponseEntity<Response> getSubmissionFilter(@Validated @RequestBody GetRequestDto request){

        HttpStatus httpCode ;
        Response resp = new Response();
        if (request.isPaginated() == true && request.isFiltered() == false){
            logger.info("Here now 1" );
            Page<Submission> response = service.findUnfilteredPage(request);
            resp.setData(response);
        } else if (request.isPaginated() == true && request.isFiltered() == true) {
            logger.info("Here now 2" );
            Page<Submission> response = service.findFilteredPage(request);
            resp.setData(response);
        } else if (request.isPaginated() == false && request.isFiltered() == true) {
            logger.info("Here now 3" );
            List<Submission> response = service.findFilteredList(request);
            resp.setData(response);
        } else if (request.isPaginated() == false && request.isFiltered() == false) {
            logger.info("Here now 4");
            List<Submission> response = service.findUnfilteredList();
            resp.setData(response);
        }
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
