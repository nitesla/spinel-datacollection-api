package com.spinel.datacollection.api.controllers;



import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.ProjectBillingRequestDto;
import com.spinel.datacollection.service.services.ProjectBillingService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(Constants.APP_CONTENT+"projectbilling")
public class ProjectBillingController {
    private final ProjectBillingService projectBillingService;

    public ProjectBillingController(ProjectBillingService projectBillingService) {
        this.projectBillingService = projectBillingService;
    }

    @PostMapping("")
    public ResponseEntity<Response> createProjectBilling(@RequestBody ProjectBillingRequestDto projectBillingRequestDto) {
        Response response = new Response(CustomResponseCode.SUCCESS,"Successful");
        response.setData(projectBillingService.createProjectBilling(projectBillingRequestDto));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProjectBilling(@RequestBody ProjectBillingRequestDto projectBillingRequestDto){
        Response response = new Response(CustomResponseCode.SUCCESS,"Update Successful");
        response.setData(projectBillingService.updateProjectBilling(projectBillingRequestDto));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProjectBilling(@PathVariable Long id){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectBillingService.getProjectBilling(id));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> searchProjectBillings(@RequestParam(value = "projectId", required = false) Long projectId,
                                                          @RequestParam(value = "page")Integer page,
                                                          @RequestParam(value = "pageSize")Integer pageSize){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectBillingService.searchAll(projectId, PageRequest.of(page,pageSize)));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByIsActive(@RequestParam(value = "isActive", required = false)Boolean isActive){
        Response response = new Response(CustomResponseCode.SUCCESS,"Record fetched Successfully");
        response.setData(projectBillingService.getActiveBillings(isActive));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@RequestBody EnableDisableDto enableDisableDto) {
        projectBillingService.enableDisableState(enableDisableDto);
        return new ResponseEntity<>(new Response(CustomResponseCode.SUCCESS,"Successful"), HttpStatus.OK);
    }
}
