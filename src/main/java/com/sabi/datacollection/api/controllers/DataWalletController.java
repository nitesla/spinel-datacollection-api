package com.sabi.datacollection.api.controllers;


import com.sabi.datacollection.core.dto.request.EnableDisableDto;
import com.sabi.datacollection.core.dto.wallet.CreateWalletDto;
import com.sabi.datacollection.service.services.DataWalletService;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(Constants.APP_CONTENT+"wallet")
public class DataWalletController {

    private final DataWalletService service;

    public DataWalletController(DataWalletService service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<Response> createWallet(@RequestBody CreateWalletDto request) {
        Response response = new Response();
        response.setData(service.createWallet(request));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getWalletById(@PathVariable Long id) {
        Response response = new Response();
        response.setData(service.findWalletById(id));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<Response> getWalletByUserId(@PathVariable Long userId) {
        Response response = new Response();
        response.setData(service.findWalletByUserId(userId));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/transactionId/{transactionId}")
    public ResponseEntity<Response> getWalletByTransactionId(@PathVariable Long transactionId) {
        Response response = new Response();
        response.setData(service.findWalletByLastTransactionId(transactionId));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/identificationNumber/{identificationNumber}")
    public ResponseEntity<Response> getWalletByIdentificationNumber(@PathVariable String identificationNumber) {
        Response response = new Response();
        response.setData(service.findWalletByIdentificationNumber(identificationNumber));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getAllWallet(@RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "pageSize") Integer pageSize) {
        Response response = new Response();
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        response.setData(service.findAll(PageRequest.of(page, pageSize)));
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

    @GetMapping("/active/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive")Boolean isActive){
        Response response = new Response();
        response.setData(service.getAll(isActive));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Record fetched successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @PostMapping("/fundWallet")
//    public ResponseEntity<Response> fundWallet(@RequestBody FundsTransferDto request) {
//        Response response = new Response();
//        service.fundWallet(request);
//        response.setCode(CustomResponseCode.SUCCESS);
//        response.setDescription("Successful");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/withdrawFunds")
//    public ResponseEntity<Response> withdrawFunds(@RequestBody FundsTransferDto request) {
//        Response response = new Response();
//        service.withdrawFunds(request);
//        response.setCode(CustomResponseCode.SUCCESS);
//        response.setDescription("Successful");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping("/transferFunds")
//    public ResponseEntity<Response> transferFunds(@RequestBody FundsTransferDto request) {
//        Response response = new Response();
//        service.transferFunds(request);
//        response.setCode(CustomResponseCode.SUCCESS);
//        response.setDescription("Successful");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
