package com.spinel.datacollection.api.controllers;



import com.spinel.datacollection.core.dto.payment.request.InitializeTransactionRequest;
import com.spinel.datacollection.core.dto.payment.request.VerifyTransaction;
import com.spinel.datacollection.service.services.DataPaymentService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"payment")
public class DataPaymentController {

    private final DataPaymentService service;

    public DataPaymentController(DataPaymentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Response response = new Response();
        response.setData(service.findPaymentById(id));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Response> findAll(@RequestParam(value = "paymentReference",required = false)String paymentReference,
                                            @RequestParam(value = "reference",required = false)String reference,
                                            @RequestParam(value = "status",required = false)String status,
                                            @RequestParam(value = "paymentMethod",required = false)Integer paymentMethod,
                                            @RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize){
        Response response = new Response();
        response.setData(service.findAll(paymentReference, reference, status, paymentMethod, PageRequest.of(page, pageSize)));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/initialize")
    public ResponseEntity<Response> initializeTransaction(@RequestBody InitializeTransactionRequest request) {
        Response response = new Response();
        response.setData(service.initializeTransaction(request));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<Response> verifyTransaction(@RequestBody VerifyTransaction request) {
        Response response = new Response();
        response.setData(service.verifyTransaction(request));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<Response> findByReference(@PathVariable String reference) {
        Response response = new Response();
        response.setData(service.findByReference(reference));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
