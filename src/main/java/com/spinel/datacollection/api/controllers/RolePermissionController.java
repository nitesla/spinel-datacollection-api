package com.spinel.datacollection.api.controllers;


import com.spinel.framework.dto.requestDto.RolePermissionDto;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.dto.responseDto.RolePermissionResponseDto;
import com.spinel.framework.models.RolePermission;
import com.spinel.framework.service.RolePermissionService;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT+"rolepermission")
public class RolePermissionController {
    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }


    /** <summary>
     * RolePermission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new RolePermission</remarks>
     */
    @PostMapping("")
    public ResponseEntity<Response> assignPermission(@Validated @RequestBody RolePermissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.assignPermission(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getRolePermission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        RolePermissionResponseDto response = service.findRolePermission(id);
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
    public ResponseEntity<Response> getRolePermissions(@RequestParam(value = "roleId",required = false)Long roleId,
                                                       @RequestParam(value = "isActive",required = false)Boolean isActive,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<RolePermission> response = service.findAll(roleId,isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removePermission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.removePermission(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Permission removed successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "roleId",required = false)Long roleId,
                                           @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<RolePermission> response = service.getAll(roleId, isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }




    @GetMapping("/permission/{roleId}")
    public ResponseEntity<Response> getPermissionsByRole(@PathVariable Long roleId){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<RolePermission> response = service.getPermissionsByRole(roleId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @PutMapping("/permission/delete/{roleId}")
    public ResponseEntity<Response> removePermissionsByRole(@PathVariable Long roleId){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.removePermission(roleId);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

}
