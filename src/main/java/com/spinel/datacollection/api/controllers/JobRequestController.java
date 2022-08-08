package com.spinel.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.JobRequestDto;
import com.sabi.datacollection.core.dto.response.JobRequestResponseDto;
import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.models.JobRequest;
import com.spinel.datacollection.service.services.JobRequestService;
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
@RequestMapping(Constants.APP_CONTENT+"jobRequest")
public class JobRequestController {
    private final JobRequestService service;

    public JobRequestController(JobRequestService service) {
        this.service = service;
    }


    /** <summary>
     * JobRequest creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new JobRequests</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createJobRequest(@Validated @RequestBody JobRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        JobRequestResponseDto response = service.createJobRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * JobRequest update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating JobRequests</remarks>
     */

    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateJobRequest(@Validated @RequestBody  JobRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        JobRequestResponseDto response = service.updateJobRequest(request);
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
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getJobRequest(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        JobRequestResponseDto response = service.findJobRequest(id);
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
    public ResponseEntity<Response> getJobRequests(@RequestParam(value = "projectId",required = false)Long projectId,
                                                 @RequestParam(value = "userId",required = false) Long userId,
                                                 @RequestParam(value = "status",required = false)String status,
//                                                 @RequestParam(value = "request")
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<JobRequest> response = service.findAll(userId, projectId, status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a JobRequest</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableJobRequest(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<JobRequest> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
