package com.spinel.datacollection.api.controllers;





import com.spinel.datacollection.core.dto.response.RoleSummaryResponseDto;
import com.spinel.datacollection.service.services.RoleSummaryService;
import com.spinel.framework.dto.requestDto.EnableDisEnableDto;
import com.spinel.framework.dto.requestDto.RoleDto;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.dto.responseDto.RoleResponseDto;
import com.spinel.framework.models.Role;
import com.spinel.framework.service.RoleService;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
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



    private final RoleService service;
    private final RoleSummaryService summaryService;

    public RoleController(RoleService service, RoleSummaryService summaryService) {
        this.service = service;
        this.summaryService = summaryService;
    }


    /** <summary>
     * Role creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new Role</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createRole(@Validated @RequestBody RoleDto request, HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = service.createRole(request,request1);
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
    public ResponseEntity<Response> updateRole(@Validated @RequestBody RoleDto request,HttpServletRequest request1){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleResponseDto response = service.updateRole(request,request1);
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
        RoleResponseDto response = service.findRole(id);
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
                                             @RequestParam(value = "clientId",required = false)Long clientId,
                                             @RequestParam(value = "isActive",required = false)Boolean isActive,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Role> response = service.findRolesByClientId(name,clientId,isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode;
        Response resp = new Response();
        List<Role> response = service.getAll(isActive);
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

    @PostMapping("roleSummary")
    public ResponseEntity<Response> roleSummary(){
        HttpStatus httpCode ;
        Response resp = new Response();
        RoleSummaryResponseDto response = summaryService.roleSummary();
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("count/user/{roleId}")
    public ResponseEntity<Response> countUsersAssignedToRole(@PathVariable Long roleId){
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(service.getCountOfActiveUsersAssignedToARole(roleId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
