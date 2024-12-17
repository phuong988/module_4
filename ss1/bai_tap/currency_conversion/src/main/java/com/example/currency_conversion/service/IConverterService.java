package com.example.currency_conversion.service;

import org.springframework.stereotype.Service;

public interface IConverterService {
    public static final double RATE = 25.4;
    boolean checkCurrency(double usd);
    double convertCurrency(double usd);
}
