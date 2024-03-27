package com.vanjok.vacation_pay_calculator.models;


public class HolidayResponse {
    double vacationPackages;

    public HolidayResponse() {
    }

    public HolidayResponse(double vacationPackages) {
        this.vacationPackages = vacationPackages;
    }

    public double getVacationPackages() {
        return vacationPackages;
    }

    public void setVacationPackages(double vacationPackages) {
        this.vacationPackages = vacationPackages;
    }
}
