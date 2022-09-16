package com.spinel.datacollection.api.controllers;

import com.spinel.datacollection.core.integrations.paystack.dto.request.InitializeTransaction;
import com.spinel.datacollection.service.integrations.paystack.service.PayStackService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.CustomResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paystack")
public class PayStackController {

    private final PayStackService payStackService;

    @PostMapping("/initialize/transaction")
    public ResponseEntity<Response> initializeTransaction(@RequestBody InitializeTransaction initializeTransaction) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(payStackService.initializeTransaction(initializeTransaction));
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/verify/transaction")
    public ResponseEntity<Response> verifyTransaction(@RequestParam(value = "reference")String reference) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(payStackService.verifyTransaction(reference));
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list/transaction")
    public ResponseEntity<Response> listTransaction(@RequestParam(value = "perPage") int perPage,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "from", required = false) String from,
                                                    @RequestParam(value = "to", required = false) String to,
                                                    @RequestParam(value = "status", required = false) String status,
                                                    @RequestParam(value = "customerId", required = false) String customerId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(payStackService.listTransaction(perPage, page, from, to, status, customerId));
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch/transaction")
    public ResponseEntity<Response> fetchTransaction(@RequestParam(value = "transactionId")String transactionId) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(payStackService.fetchTransaction(transactionId));
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/total/transaction")
    public ResponseEntity<Response> totalTransactions(@RequestParam(value = "perPage") int perPage,
                                                      @RequestParam(value = "page") int page,
                                                      @RequestParam(value = "from", required = false) String from,
                                                      @RequestParam(value = "to", required = false) String to) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setData(payStackService.totalTransactions(perPage, page, from, to));
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
