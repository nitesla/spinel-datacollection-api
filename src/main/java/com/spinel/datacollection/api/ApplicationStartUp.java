//package com.spinel.datacollection.api;
//
//
//
//import com.spinel.framework.globaladminintegration.AccessTokenService;
//import com.spinel.framework.service.ExternalTokenService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class ApplicationStartUp implements ApplicationRunner {
//
//    private final ExternalTokenService externalTokenService;
//
//    private final AccessTokenService accessTokenService;
//
//
//    @Override
//    public void run (ApplicationArguments args){
//        log.info("Generate new space token at : {}" , new Date());
//        externalTokenService.externalTokenRequest();
//        accessTokenService.globalTokenRequest();
//        log.info("Generate global admin token at : {}" , new Date());
//    }
//}
