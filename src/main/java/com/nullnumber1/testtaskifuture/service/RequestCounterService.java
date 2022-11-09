package com.nullnumber1.testtaskifuture.service;

public interface RequestCounterService {
    long getTotalCounter();
    long getPerHourCounter();
    void increment();
    void resetPerHourCounter();
}
