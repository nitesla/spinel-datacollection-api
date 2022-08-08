package com.spinel.datacollection.api.runner;




import com.spinel.framework.globaladminintegration.AccessTokenService;
import com.spinel.framework.service.ExternalTokenService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@SuppressWarnings("ALL")
@Slf4j
@Service
@Component
public class CronJob {

    private ExternalTokenService externalTokenService;
    private final AccessTokenService accessTokenService;

    public CronJob(ExternalTokenService externalTokenService, AccessTokenService accessTokenService) {
        this.externalTokenService = externalTokenService;
        this.accessTokenService = accessTokenService;

    }



    @Scheduled(cron = "${tokenGen}")
    @SchedulerLock(name = "getNewToken", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void getNewToken() {
        log.info("::::::::::::: Cron Job Started at :::::::::::: :   %s", new Date());
        externalTokenService.externalTokenRequest();
    }




    @Scheduled(cron = "${globalAdminToken}")
    @SchedulerLock(name = "globalToken", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void globalToken() {
        log.info("::::::::::::: global token Cron Job Started at :::::::::::: :   %s", new Date());
        accessTokenService.globalTokenRequest();
    }

}
