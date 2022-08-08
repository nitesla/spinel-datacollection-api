package com.spinel.datacollection.api.controllers;



import com.spinel.datacollection.core.dto.request.EnableDisableDto;
import com.spinel.datacollection.core.dto.request.TransactionDto;
import com.spinel.datacollection.core.dto.response.TransactionResponseDto;
import com.spinel.datacollection.core.models.Transaction;
import com.spinel.datacollection.service.services.TransactionService;
import com.spinel.framework.dto.responseDto.Response;
import com.spinel.framework.utils.Constants;
import com.spinel.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.sabi.datacollection.core.enums.ActionType;
import com.sabi.datacollection.core.enums.Status;
import com.sabi.datacollection.core.enums.TransactionType;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;


@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"transaction")
public class TransactionController {


    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }


    /** <summary>
     * Transaction creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new transactions</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createTransaction(@Validated @RequestBody TransactionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TransactionResponseDto response = service.createTransaction(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Transaction update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating transactions</remarks>
     */

    @PutMapping("")
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> updateTransaction(@Validated @RequestBody  TransactionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        TransactionResponseDto response = service.updateTransaction(request);
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
    // @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_CREATE_USER')")
    public ResponseEntity<Response> getTransaction(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        TransactionResponseDto response = service.findTransaction(id);
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

    @GetMapping("/page")
    public ResponseEntity<Response> getTransactions(
                                        @RequestParam(value = "walletId",required = false)Long walletId,
                                        @RequestParam(value = "amount",required = false) BigDecimal amount,
                                        @RequestParam(value = "initialBalance",required = false) BigDecimal initialBalance,
                                        @RequestParam(value = "finalBalance",required = false) BigDecimal finalBalance,
                                        @RequestParam(value = "actionType",required = false) ActionType actionType,
                                        @RequestParam(value = "transactionType",required = false) TransactionType transactionType,
                                        @RequestParam(value = "status",required = false) Status status,
                                        @RequestParam(value = "reference",required = false)String reference,
                                        @RequestParam(value = "fromDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                        @RequestParam(value = "toDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                        @RequestParam(value = "page") int page,
                                        @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Transaction> response = service.findAll(walletId, amount, initialBalance,finalBalance,actionType,transactionType,status, reference,fromDate,toDate, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a Transaction</remarks>
     */

    @PutMapping("/enabledisenable")
    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisEnableTransaction(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<Transaction> response = service.getAll(isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


}
