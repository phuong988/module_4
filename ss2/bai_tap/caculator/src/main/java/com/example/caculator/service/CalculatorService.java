package com.example.caculator.service;
import exception.InvalidInputException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements ICalculatorService{

    public double calculate(double num1, double num2, String operation) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid operation");
        }
        return result;
    }

    public double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid input: " + value + ". Please enter a valid number.");
        }
    }
}

