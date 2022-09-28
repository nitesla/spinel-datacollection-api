package com.spinel.datacollection.api.controllers;



import com.spinel.datacollection.core.dto.payment.request.*;
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

    @PostMapping("/verify")
    public ResponseEntity<Response> verifyTransaction(@RequestBody VerifyTransaction request) {
        Response response = new Response();
        response.setData(service.verifyTransaction(request));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Response> listTransaction(@RequestParam (value = "perPage") int perPage,
                                                    @RequestParam (value = "page") int page,
                                                    @RequestParam (value = "from", required = false) String from,
                                                    @RequestParam (value = "to", required = false) String to,
                                                    @RequestParam (value = "status", required = false) String status,
                                                    @RequestParam (value = "customer", required = false) String customer,
                                                    @RequestParam (value = "paymentProvider") String paymentProvider) {
        Response response = new Response();
        response.setData(service.listTransactions(perPage, page, from, to, status, customer, paymentProvider));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<Response> fetchTransaction(@RequestParam (value = "transactionId") String transactionId,
                                                     @RequestParam (value = "paymentProvider") String paymentProvider) {
        Response response = new Response();
        response.setData(service.fetchTransactions(transactionId, paymentProvider));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Response> totalTransaction(@RequestParam (value = "perPage") int perPage,
                                                     @RequestParam (value = "page") int page,
                                                     @RequestParam (value = "from", required = false) String from,
                                                     @RequestParam (value = "to", required = false) String to,
                                                     @RequestParam (value = "paymentProvider") String paymentProvider) {
        Response response = new Response();
        response.setData(service.totalTransactions(perPage, page, from, to, paymentProvider));
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

    @PostMapping("/validate/customer")
    public ResponseEntity<Response> validateCustomer(@RequestBody ValidateCustomer validateCustomer) {
        Response response = new Response();
        response.setData(service.validateCustomer(validateCustomer));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/resolve/account")
    public ResponseEntity<Response> resolveAccountNumber(@RequestBody ResolveAccountNumber resolveAccountNumber) {
        Response response = new Response();
        response.setData(service.resolveAccountNumber(resolveAccountNumber));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/single/transfer")
    public ResponseEntity<Response> singleTransfer(@RequestBody SingleTransfer singleTransfer) {
        Response response = new Response();
        response.setData(service.singleTransfer(singleTransfer));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/subscription/create")
    public ResponseEntity<Response> createSubscription(@RequestBody CreateSubscription createSubscription) {
        Response response = new Response();
        response.setData(service.createSubscription(createSubscription));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/resolve/card/bin")
    public ResponseEntity<Response> resolveCardBin(@RequestBody ResolveCardBin resolveCardBin) {
        Response response = new Response();
        response.setData(service.resolveCardBin(resolveCardBin));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/charge/authorization")
    public ResponseEntity<Response> chargeAuthorization(@RequestBody ChargeAuthorization chargeAuthorization) {
        Response response = new Response();
        response.setData(service.chargeAuthorization(chargeAuthorization));
        response.setCode(CustomResponseCode.SUCCESS);
        response.setDescription("Successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/paystack/webhook")
    public void paystackWebhookListener(@RequestBody String body){
        service.paystackWebhookListener(body);
    }

}
