package com.spinel.datacollection.api.controllers;



import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.FormDto;
import com.spinel.datacollection.core.dto.request.GetRequestDto;
import com.spinel.datacollection.core.dto.response.FormResponseDto;
import com.spinel.datacollection.core.models.Form;
import com.spinel.datacollection.service.services.FormService;
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
@RequestMapping(Constants.APP_CONTENT+"form")
public class FormController {


    private final FormService service;

    private  static final Logger logger = LoggerFactory.getLogger(FormController.class);


    public FormController(FormService service) {
        this.service = service;
    }


    /** <summary>
     * Form creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new forms</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createForm(@Validated @RequestBody FormDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        FormResponseDto response = service.createForm(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("/createbulk")
    public ResponseEntity<Response> createBulkForm(@Validated @RequestBody List<FormDto> requestLists){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<FormResponseDto> response = service.createBulkForm(requestLists);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Form update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating forms</remarks>
     */

//    @PutMapping("")
//    public ResponseEntity<Response> updateForm(@Validated @RequestBody  FormDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        FormResponseDto response = service.updateForm(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }



    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getForm(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        FormResponseDto response = service.findForm(id);
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
    public ResponseEntity<Response> getForms(@RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "version",required = false)String version,
                                             @RequestParam(value = "description",required = false)String description,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Form> response = service.findAll(name, version, description, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("/filter")
    public ResponseEntity<Response> getFormFilter(@Validated @RequestBody GetRequestDto request){

        HttpStatus httpCode ;
        Response resp = new Response();
        if (request.isPaginated() == true && request.isFiltered() == false){
            logger.info("Here now 1" );
            Page<Form> response = service.findUnfilteredPage(request);
            resp.setData(response);
        } else if (request.isPaginated() == true && request.isFiltered() == true) {
            logger.info("Here now 2" );
            Page<Form> response = service.findFilteredPage(request);
            resp.setData(response);
        } else if (request.isPaginated() == false && request.isFiltered() == true) {
            logger.info("Here now 3" );
            List<Form> response = service.findFilteredList(request);
            resp.setData(response);
        } else if (request.isPaginated() == false && request.isFiltered() == false) {
            logger.info("Here now 4");
            List<Form> response = service.findUnfilteredList();
            resp.setData(response);
        }
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Form</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableForm(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive,
                                                   @RequestParam(value = "projectId",required = false)Long projectId,
                                                   @RequestParam(value = "userId",required = false)Long userId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Form> response = service.getAll(isActive,projectId, userId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PostMapping("/newfilter")
    public ResponseEntity<Response> getNewFormFilter(@Validated @RequestBody GetRequestDto request){

        HttpStatus httpCode ;
        Response resp = new Response();
        List response = service.findGroupPage(request);
        resp.setData(response);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
