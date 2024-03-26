package com.vanjok.vacation_pay_calculator.util;

public class CalculatorErrorResponse {
    private String message;

    public CalculatorErrorResponse(String massage) {
        this.message = massage;
    }

    public String getMassage() {
        return message;
    }

    public void setMassage(String massage) {
        this.message = massage;
    }
}
