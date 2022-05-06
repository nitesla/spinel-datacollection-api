package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.EnableDisEnableDto;
import com.sabi.datacollection.core.dto.request.DataRoleDto;
import com.sabi.datacollection.core.dto.response.DataRoleResponseDto;
import com.sabi.datacollection.core.models.DataRole;
import com.sabi.datacollection.service.services.DataRoleService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"role")
public class RoleController {



    private final DataRoleService service;

    public RoleController(DataRoleService service) {
        this.service = service;
    }


    /** <summary>
     * Role creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Role</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createRole(@Validated @RequestBody DataRoleDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DataRoleResponseDto response = service.createRole(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Role update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating role</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateRole(@Validated @RequestBody DataRoleDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        DataRoleResponseDto response = service.updateRole(request,request1);
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
    public ResponseEntity<Response> getRole(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        DataRoleResponseDto response = service.findRole(id);
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
    @GetMapping("")
    public ResponseEntity<Response> getRoles(@RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "status",required = false)Integer status,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DataRole> response = service.findAll(name,status, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(){
        HttpStatus httpCode;
        Response resp = new Response();
        List<DataRole> response = service.getAll();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * Enable disable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disabling a Product</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request,request1);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
