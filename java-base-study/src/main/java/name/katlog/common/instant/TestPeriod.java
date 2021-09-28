package name.katlog.common.instant;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

/**
 * 2021/9/26
 *
 * 以年、月或者日的方式对多个时间单位建模，可用Period类
 */
public class TestPeriod {

    @Test
    public void _static_between(){
        Period tenDays = Period.between(LocalDate.of(2021, 3, 8), LocalDate.of(2021, 4, 18));
        System.out.println("tenDays = " + tenDays.getDays());
        System.out.println("tenDays = " + tenDays.getMonths());
    }
}
