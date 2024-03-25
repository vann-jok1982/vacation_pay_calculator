package com.vanjok.vacation_pay_calculator.servis;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculatorServis {
    @Autowired
    HolidayResponse holidayResponse;


    public HolidayResponse calculation(HolidayRequest holidayRequest){
        double vacationPackages=holidayRequest.getAverageSalary()/29.3*holidayRequest.getVacationDays();

        BigDecimal bd = new BigDecimal(vacationPackages); // округление до ближайшего числа с указанной точностью
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        vacationPackages = bd.doubleValue();

        holidayResponse.setVacationPackages(vacationPackages);
        return holidayResponse;
    }
}
