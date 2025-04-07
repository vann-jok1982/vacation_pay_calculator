package com.vanjok.vacation_pay_calculator.models;

import java.time.LocalDate;

public class HolidayRequest {
    int vacationDays;//кол-во дней отпуска
    int averageSalary;//средняя зарплата
    LocalDate vacationStartDate;//дата начала отпуска

    public HolidayRequest(int vacationDays, int averageSalary, LocalDate vacationStartDate) {
        this.vacationDays = vacationDays;
        this.averageSalary = averageSalary;
        this.vacationStartDate = vacationStartDate;
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

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }
}
