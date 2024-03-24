package com.vanjok.vacation_pay_calculator.controllers;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calkulator")
public class CalculatorController {
//    @Autowired
//    HolidayRequest holidayRequest;

    @Autowired
    HolidayResponse holidayResponse;

    @GetMapping("/{averageSalary}/{vacationDays}")
    public HolidayResponse CalkulatorZp(@PathVariable("averageSalary") double averageSalary ,
                                       @PathVariable("vacationDays") int vacationDays ){

//        holidayRequest.setAverageSalary(averageSalary);
//        holidayRequest.setVacationDays(vacationDays);

        double vacationPackages=averageSalary/29*vacationDays;
        holidayResponse.setVacationPackages(vacationPackages);
        return holidayResponse;
    }
}
