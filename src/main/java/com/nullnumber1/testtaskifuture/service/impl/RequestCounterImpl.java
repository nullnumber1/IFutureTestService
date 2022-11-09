package com.nullnumber1.testtaskifuture.service.impl;

import com.nullnumber1.testtaskifuture.service.RequestCounterService;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@ManagedResource

public class RequestCounterImpl implements RequestCounterService {
    private AtomicLong counterTotal;
    private AtomicLong counterPerHour;

    public RequestCounterImpl() {
        counterTotal = new AtomicLong(0);
        counterPerHour = new AtomicLong(0);
    }

    @ManagedAttribute
    public long getTotalCounter() {
        return counterTotal.get();
    }

    @ManagedAttribute
    public long getPerHourCounter() {
        return counterPerHour.get();
    }

    public void increment() {
        counterTotal.incrementAndGet();
        counterPerHour.incrementAndGet();
    }

    @ManagedOperation
    public void resetPerHourCounter() {
        counterPerHour.set(0);
    }
}
