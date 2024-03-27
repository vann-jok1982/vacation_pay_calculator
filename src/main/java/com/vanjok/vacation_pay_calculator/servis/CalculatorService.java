package com.vanjok.vacation_pay_calculator.servis;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.util.CalculatorInvalidArgumentException;
import com.vanjok.vacation_pay_calculator.util.CalculatorNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculatorService {

    public HolidayResponse calculation(HolidayRequest holidayRequest)  {

        if (holidayRequest == null)
            throw new CalculatorNotFoundException("пустой запрос");

        if (holidayRequest.getVacationDays() <= 0 || holidayRequest.getAverageSalary() <= 0)
            throw new CalculatorInvalidArgumentException("отрицательное число");

        double vacationPackages = holidayRequest.getAverageSalary() / 29.3 * holidayRequest.getVacationDays();

        BigDecimal bd = new BigDecimal(vacationPackages); // округление до ближайшего числа с указанной точностью
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        vacationPackages = bd.doubleValue();

        return new HolidayResponse(vacationPackages);

    }
}
