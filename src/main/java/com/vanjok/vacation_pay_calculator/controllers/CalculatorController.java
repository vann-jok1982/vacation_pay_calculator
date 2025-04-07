package com.vanjok.vacation_pay_calculator.controllers;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.servis.CalculatorService;
import com.vanjok.vacation_pay_calculator.util.CalculatorErrorResponse;
import com.vanjok.vacation_pay_calculator.util.CalculatorInvalidArgumentException;
import com.vanjok.vacation_pay_calculator.util.CalculatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;

@RestController
@RequestMapping("/calсulator")
public class CalculatorController {


    @Autowired
    CalculatorService calculatorService;

    @GetMapping
    public ResponseEntity<HolidayResponse> getVacationPay(
            @RequestParam(value = "averageSalary") int averageSalary,
            @RequestParam(value = "vacationDays") int vacationDays,
            @RequestParam(value = "vacationStartDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStartDate
    ) {

        //Создаем request object
        HolidayRequest holidayRequest;
        if (vacationStartDate != null) {
            holidayRequest = new HolidayRequest(vacationDays,averageSalary, vacationStartDate);
        } else {
            holidayRequest = new HolidayRequest(vacationDays,averageSalary);
        }

        return ResponseEntity.ok(calculatorService.calculation(holidayRequest));

    }

    /*
     * @ExceptionHandler - это аннотация, которая указывает, что данный метод обрабатывает исключения типа CalculatorNotFoundException.
     * Обработчик исключений позволяет централизованно обрабатывать исключения и возвращать пользователю понятные сообщения об ошибках.
     */
    @ExceptionHandler
    private ResponseEntity<CalculatorErrorResponse> handlerException(CalculatorNotFoundException e) {
        CalculatorErrorResponse response = new CalculatorErrorResponse(
                "Отсутствуют параметры запроса"

        );
        /*
         * return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); - создает HTTP-ответ с кодом 400 (Bad Request) и телом ответа, содержащим информацию об ошибке.
         * HttpStatus.BAD_REQUEST - это константа, которая представляет HTTP-код 400 (Bad Request).
         */
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CalculatorErrorResponse> handlerException(CalculatorInvalidArgumentException e) {
        CalculatorErrorResponse response = new CalculatorErrorResponse(
                "В запросе могут быть только цифры больше нуля"

        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    // Добавляем обработчик для MethodArgumentTypeMismatchException
    private ResponseEntity<CalculatorErrorResponse> handleDateParseException(MethodArgumentTypeMismatchException ex) {
        CalculatorErrorResponse response = new CalculatorErrorResponse("Неверный формат даты. Используйте ISO формат YYYY-MM-DD.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}