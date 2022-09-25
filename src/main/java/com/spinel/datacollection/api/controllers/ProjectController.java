package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.ProjectDto;
import com.spinel.datacollection.core.dto.response.ProjectResponseDto;
import com.spinel.datacollection.core.models.Project;
import com.spinel.datacollection.service.services.ProjectService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"project")
public class ProjectController {

    private final ProjectService service;


    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProject(@RequestBody ProjectDto projectDto, HttpServletRequest request) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.createProject(projectDto, request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProject(@RequestBody ProjectDto request, HttpServletRequest request1) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.updateProject(request, request1);
        response.setDescription("Update Successful");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProject(@PathVariable Long id, HttpServletRequest request1) {
        Response response = new Response();
        ProjectResponseDto projectResponse = service.findProjectById(id, request1);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getProjects(@RequestParam(value = "name",required = false)String name,
                                                @RequestParam(value = "status",required = false) String status,
                                                @RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<Project> projectPage = service.findAll(name, status, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully !");
        response.setData(projectPage);
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
        List<Project> projectPage = service.getAll(isActive);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(projectPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projectowner/{projectOwnerId}")
    public ResponseEntity<Response> getProjectByProjectOwnerId(@PathVariable Long projectOwnerId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        response.setData(service.findProjectByProjectOwner(projectOwnerId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping("/category/{id}")
//    public ResponseEntity<Response> getProjectByProjectCategoryId(@PathVariable Long categoryId) {
//        Response response = new Response();
//        response.setCode(CustomResponseCode.SUCCESS);
//        response.setDescription("Record fetched successfully!");
//        response.setData(service.findProjectByCategory(categoryId));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping("/category/status")
//    public ResponseEntity<Response> getProjectByCategoryAndStatus(@RequestParam(value = "status")String status,
//                                                                  @RequestParam(value = "categoryId",required = false) Long categoryId) {
//        Response response = new Response();
//        response.setCode(CustomResponseCode.SUCCESS);
//        response.setDescription("Record fetched successfully!");
//        response.setData(service.findProjectByStatusAndCategory(status, categoryId));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/audittrail")
    public ResponseEntity<Response> getProjectAuditTrail(@RequestParam(value = "projectName",required = false) String projectName,
                                                         @RequestParam(value = "auditTrailFlag",required = false) String auditTrailFlag,
                                                         @RequestParam(value = "page") Integer page,
                                                         @RequestParam(value = "pageSize") Integer pageSize){
        Response resp = new Response();
        resp.setData(service.getProjectAuditTrail(projectName, auditTrailFlag, PageRequest.of(page, pageSize)));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }



    @GetMapping("getProjectSummary")
    public ResponseEntity<Response> getProjectSummary(@RequestParam(value = "projectOwnerId") Long projectOwnerId){
        Response resp = new Response();
        resp.setData(service.getProjectSummary(projectOwnerId));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("getRecentProjects")
    public ResponseEntity<Response> getRecentProjects(@RequestParam(value = "projectOwnerId") Long projectOwnerId,
                                                      @RequestParam(value = "count") Integer count){
        Response resp = new Response();
        resp.setData(service.getRecentProjects(projectOwnerId, count));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("getProjectDrafts")
    public ResponseEntity<Response> getProjectDrafts(@RequestParam(value = "projectOwnerId") Long projectOwnerId,
                                                     @RequestParam(value = "count") Integer count){
        Response resp = new Response();
        resp.setData(service.getDrafts(projectOwnerId, count));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successfully");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

}
