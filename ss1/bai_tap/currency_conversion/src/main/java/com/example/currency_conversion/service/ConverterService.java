package com.example.currency_conversion.service;

import org.springframework.stereotype.Service;

@Service
public class ConverterService implements IConverterService{
    @Override
    public boolean checkCurrency(double usd) {
        return usd >= 0;
    }

    @Override
    public double convertCurrency(double usd) {
        return usd * RATE;
    }
}
