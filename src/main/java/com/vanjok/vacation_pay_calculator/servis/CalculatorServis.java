package com.vanjok.vacation_pay_calculator.servis;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.util.CalculatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculatorServis {
    @Autowired
    HolidayResponse holidayResponse;


    public HolidayResponse calculation(HolidayRequest holidayRequest)  {

        if (holidayRequest.getVacationDays() <= 0 || holidayRequest.getAverageSalary() <= 0)
            throw new CalculatorNotFoundException("отрицательное число");
        if (holidayRequest == null)
            throw new CalculatorNotFoundException("пустой запрос");

        double vacationPackages = holidayRequest.getAverageSalary() / 29.3 * holidayRequest.getVacationDays();

        BigDecimal bd = new BigDecimal(vacationPackages); // округление до ближайшего числа с указанной точностью
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        vacationPackages = bd.doubleValue();
        holidayResponse.setVacationPackages(vacationPackages);

        return holidayResponse;

    }
}
