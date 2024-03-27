package com.vanjok.vacation_pay_calculator.controllers;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.servis.CalculatorService;
import com.vanjok.vacation_pay_calculator.util.CalculatorErrorResponse;
import com.vanjok.vacation_pay_calculator.util.CalculatorInvalidArgumentException;
import com.vanjok.vacation_pay_calculator.util.CalculatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calсulator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<HolidayResponse> PostResponse(@RequestBody HolidayRequest holidayRequest) {

        return ResponseEntity.ok(calculatorService.calculation(holidayRequest));

    }
    @ExceptionHandler
    private ResponseEntity <CalculatorErrorResponse> handlerException(CalculatorNotFoundException e){
        CalculatorErrorResponse response=new CalculatorErrorResponse(
                "Отсутствуют параметры запроса"

        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity <CalculatorErrorResponse> handlerException(CalculatorInvalidArgumentException e){
        CalculatorErrorResponse response=new CalculatorErrorResponse(
                "В запросе могут быть только цифры больше нуля"

        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
