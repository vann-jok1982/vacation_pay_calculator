
import com.vanjok.vacation_pay_calculator.VacationPayCalculatorApplication; // Замените на ваш основной класс
import com.vanjok.vacation_pay_calculator.models.HolidayRequest;
import com.vanjok.vacation_pay_calculator.models.HolidayResponse;
import com.vanjok.vacation_pay_calculator.servis.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired; // Добавьте этот импорт
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = VacationPayCalculatorApplication.class) // **Укажите класс приложения**
public class CalculatorServiceTest {

    @Autowired // Добавьте эту аннотацию
    private  CalculatorService calculatorService;

    @Test
    void testCalculationWithoutStartDate() {
        HolidayRequest request = new HolidayRequest(14, 60000, null);
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(28668.94, response.getVacationPackages(), 0.01);
    }

    @Test
    void testCalculationWithStartDateAndNoHolidaysOrWeekends() {
        // 5 рабочих дней в периоде отпуска
        HolidayRequest request = new HolidayRequest(5, 60000, LocalDate.of(2024, 6, 17)); // Пн
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(10238.91, response.getVacationPackages(), 0.01);
    }

    @Test
    void testCalculationWithStartDateAndWeekends() {
        // 7 дней отпуска, 5 рабочих дней
        HolidayRequest request = new HolidayRequest(7, 60000, LocalDate.of(2024, 6, 17)); // Пн
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(10238.91, response.getVacationPackages(), 0.01);
    }

    @Test
    void testCalculationWithStartDateAndHolidays() {
        //  10 дней, с одним праздником (12 июня) - День России, 7 рабочих дней
        HolidayRequest request = new HolidayRequest(10, 60000, LocalDate.of(2024, 6, 10));
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(14334.47, response.getVacationPackages(), 0.01);
    }

    @Test
    void testCalculationWithStartDateAndHolidaysAndWeekends() {
        // 14 дней отпуска,  с праздником 12 июня - День России, 9 рабочих дней
        HolidayRequest request = new HolidayRequest(14, 60000, LocalDate.of(2024, 6, 10));
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(18430.03, response.getVacationPackages(), 0.01);
    }

    @Test
    void testCalculationWithDifferentSalary() {
        HolidayRequest request = new HolidayRequest(14, 100000, LocalDate.of(2024, 6, 10));
        HolidayResponse response = calculatorService.calculation(request);
        assertEquals(30716.72, response.getVacationPackages(), 0.01);
    }
}