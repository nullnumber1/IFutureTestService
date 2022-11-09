package com.nullnumber1.testtaskifuture.config;

import com.nullnumber1.testtaskifuture.service.impl.RequestCounterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    private final RequestCounterImpl requestCounterImpl;

    @Autowired
    public ScheduleConfig(RequestCounterImpl requestCounterImpl) {
        this.requestCounterImpl = requestCounterImpl;
    }

    @Scheduled(cron = "0 0 * * * *", zone = "Europe/Moscow")
    public void resetPerHourCounter() {
        requestCounterImpl.resetPerHourCounter();
    }
}
