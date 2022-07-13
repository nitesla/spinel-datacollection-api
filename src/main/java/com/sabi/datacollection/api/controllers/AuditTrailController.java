package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.models.DataAuditTrail;
import com.sabi.datacollection.service.services.DataAuditTrailService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.models.AuditTrail;
import com.sabi.framework.service.AsyncService;
import com.sabi.framework.service.AuditTrailService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"audit")
public class AuditTrailController {

    @Autowired
    private AsyncService asyncService;
    private final DataAuditTrailService service;

    public AuditTrailController(DataAuditTrailService service) {
        this.service = service;
    }






    @GetMapping("/{id}")
    public ResponseEntity<Response> getAudit(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DataAuditTrail response = service.getAudit(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("")
    public ResponseEntity<Response> audits(@RequestParam(value = "username", required = false) String username,
                                           @RequestParam(value = "event", required = false) String event,
                                           @RequestParam(value = "flag", required = false) String flag,
                                           @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                           @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "pageSize") int pageSize) {

        HttpStatus httpCode;
        Response resp = new Response();
        Page<DataAuditTrail> response = service.findAll(username,event,flag, startDate, endDate, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

//    @GetMapping("/getSingleUserAudit")
//    public ResponseEntity<Response> getSingleUserAudit(@RequestParam(value = "username")String username,
//                                                       @RequestParam(value = "page") int page,
//                                                       @RequestParam(value = "pageSize") int pageSize){
//        Response resp = new Response();
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(service.getUserAudit(username,  PageRequest.of(page, pageSize)));
//        return new ResponseEntity<>(resp, HttpStatus.OK);
//    }
}