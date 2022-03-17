package com.sabi.datacollection.api.controllers;

import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.request.ProjectFormRequestDto;
import com.sabi.datacollection.service.services.ProjectFormService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sabi.framework.utils.Constants;

import java.util.List;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectform")
public class ProjectFormController {

    private final ProjectFormService projectFormService;

    public ProjectFormController(ProjectFormService projectFormService) {
        this.projectFormService = projectFormService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectForm(@RequestBody ProjectFormRequestDto projectFormRequestDto) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Successful");
        response.setData(projectFormService.createProjectForm(projectFormRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<Response> createProjectFormsInBulk(@RequestBody List<ProjectFormRequestDto> projectFormRequestDtoList) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Successful");
        response.setData(projectFormService.createsProjectFormsInBulk(projectFormRequestDtoList));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectForm(@RequestBody ProjectFormRequestDto projectFormRequestDto) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
        response.setData(projectFormService.updateProjectForm(projectFormRequestDto));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/bulk")
    public ResponseEntity<Response> updateProjectFormsInBulk(@RequestBody List<ProjectFormRequestDto> projectFormRequestDtoList) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
        response.setData(projectFormService.updatesProjectFormsInBulk(projectFormRequestDtoList));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectForm(@PathVariable Long id){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectFormService.getProjectForm(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> searchProjectForms(@RequestParam(value = "projectId", required = false)Long projectId,
                                                    @RequestParam(value = "formId", required = false)Long formId,
                                                    @RequestParam(value = "page")Integer page,
                                                    @RequestParam(value = "pageSize")Integer pageSize){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectFormService.searchAll(projectId,formId, PageRequest.of(page,pageSize)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByIsActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectFormService.getActiveForms(isActive));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto enableDisableDto){
        projectFormService.enableDisableState(enableDisableDto);
        return new ResponseEntity<>(new Response(CustomResponseCode.SUCCESS,"Successful"), HttpStatus.OK);
    }
}
