package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.DataRolePermissionDto;
import com.sabi.datacollection.core.dto.response.DataRolePermissionResponseDto;
import com.sabi.datacollection.core.models.DataRolePermission;
import com.sabi.datacollection.service.services.DataRolePermissionService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(Constants.APP_CONTENT+"rolepermission")
public class RolePermissionController {
    private final DataRolePermissionService service;

    public RolePermissionController(DataRolePermissionService service) {
        this.service = service;
    }


    /** <summary>
     * RolePermission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new RolePermission</remarks>
     */
    @PostMapping("")
    public ResponseEntity<Response> assignPermission(@Validated @RequestBody DataRolePermissionDto request){
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
        DataRolePermissionResponseDto response = service.findRolePermission(id);
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
                                                       @RequestParam(value = "status",required = false)int status,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<DataRolePermission> response = service.findAll(roleId,status, PageRequest.of(page, pageSize));
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

//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "roleId",required = false)Long roleId,
//                                           @RequestParam(value = "isActive")Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<RolePermission> response = service.getAll(roleId, isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }




//    @GetMapping("/permission/{roleId}")
//    public ResponseEntity<Response> getPermissionsByRole(@PathVariable Long roleId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<RolePermission> response = service.getPermissionsByRole(roleId);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }

}
