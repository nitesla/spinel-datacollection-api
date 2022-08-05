package com.spinel.datacollection.api.controllers;


import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.response.ProjectOwnerEnumeratorResponseDto;
import com.spinel.datacollection.core.models.ProjectOwnerEnumerator;
import com.spinel.datacollection.service.services.ProjectOwnerEnumeratorService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"projectownerenumerator")
public class ProjectOwnerEnumeratorController {

    private final ProjectOwnerEnumeratorService service;

    public ProjectOwnerEnumeratorController(ProjectOwnerEnumeratorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectOwnerEnumeratorById(@PathVariable Long id) {
        Response response = new Response();
        ProjectOwnerEnumeratorResponseDto projectOwnerEnumeratorResponseDto = service.findProjectOwnerEnumeratorById(id);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerEnumeratorResponseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Response> getProjectOwnerEnumeratorByEnumeratorId(@PathVariable Long enumeratorId,
                                                                @RequestParam(value = "page") Integer page,
                                                                @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectOwnerEnumerator> projectOwnerUserPage = service.findProjectOwnerEnumeratorByEnumeratorId(enumeratorId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerUserPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/projectOwner/{projectOwnerId}")
    public ResponseEntity<Response> getProjectOwnerUserByOwnerId(@PathVariable Long projectOwnerId,
                                                                 @RequestParam(value = "page") Integer page,
                                                                 @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        Page<ProjectOwnerEnumerator> projectOwnerUserPage = service.findProjectOwnerEnumeratorByProjectOwnerId(projectOwnerId, PageRequest.of(page, pageSize));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched Successfully");
        response.setData(projectOwnerUserPage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisableDto request) {
        Response response = new Response();
        service.enableDisableState(request);
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
