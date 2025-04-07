package com.vanjok.vacation_pay_calculator.servis;

import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;

@Component
public class CalculatorService {
    // Список праздничных дней (формат MM-DD)
    private static final List<MonthDay> HOLIDAYS = Arrays.asList(
            MonthDay.of(1, 1),  // Новый год
            MonthDay.of(1, 2),  // Новый год
            MonthDay.of(1, 3),  // Новый год
            MonthDay.of(1, 4),  // Новый год
            MonthDay.of(1, 5),  // Новый год
            MonthDay.of(1, 8),  // Новый год
            MonthDay.of(2, 23), // День защитника Отечества
            MonthDay.of(3, 8),  // Международный женский день
            MonthDay.of(5, 1),  // Праздник Весны и Труда
            MonthDay.of(5, 9),  // День Победы
            MonthDay.of(6, 12), // День России
            MonthDay.of(11, 4)  // День народного единства
    );

    public HolidayResponse calculation(HolidayRequest holidayRequest) {
        double vacationPackages ;
        LocalDate vacationStartDate = holidayRequest.getVacationStartDate();
        int vacationDays = holidayRequest.getVacationDays();
        int averageSalary = holidayRequest.getAverageSalary();

        if (vacationStartDate == null) {
            vacationPackages = (double) averageSalary / 29.3 * vacationDays;

            BigDecimal bd = new BigDecimal(vacationPackages); // округление до ближайшего числа с указанной точностью
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            vacationPackages = bd.doubleValue();
        } else {
            LocalDate vacationEndDate = vacationStartDate.plusDays(vacationDays - 1);
            List<LocalDate> allVacationDates = vacationStartDate.datesUntil(vacationEndDate.plusDays(1))
                    .toList();

            long workingDays = allVacationDates.stream()
                    .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) // Исключаем выходные
                    .filter(date -> !HOLIDAYS.contains(MonthDay.from(date))) // Исключаем праздничные дни
                    .count();
            vacationPackages = (double) averageSalary / 29.3 * workingDays;

            BigDecimal bd = new BigDecimal(vacationPackages); // округление до ближайшего числа с указанной точностью
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            vacationPackages = bd.doubleValue();
        }
        return new HolidayResponse(vacationPackages);

    }
}
