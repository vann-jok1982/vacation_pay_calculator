package com.vanjok.vacation_pay_calculator.controllers;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.servis.CalculatorServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calсulator")
public class CalculatorController {

    @Autowired
    CalculatorServis calculatorServis;

    @GetMapping
    public ResponseEntity<HolidayResponse> PostResponse(@RequestBody HolidayRequest holidayRequest) {

        return ResponseEntity.ok(calculatorServis.calculation(holidayRequest));
    }
}
