package com.vanjok.vacation_pay_calculator.models;

public class HolidayRequest {
    int vacationDays;//кол-во дней отпуска
    int averageSalary;//средняя зарплата

    public HolidayRequest() {
    }

    public HolidayRequest(int vacationDays, int averageSalary) {
        this.vacationDays = vacationDays;
        this.averageSalary = averageSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(int averageSalary) {
        this.averageSalary = averageSalary;
    }
}
