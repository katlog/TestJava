package name.katlog.refactor._07movecharacter;

import java.util.Date;

/**
 * Created by fw on 2018/4/24
 * 引入外加函数
 */
public class IntroduceForeignMethod {
    class BeforeRefactor {
        Date previousEnd;

        Date newStart = new Date(previousEnd.getYear(), previousEnd.getMonth(), previousEnd.getDate() + 1);

    }

    static class Refactor{
        private Date previousEnd;

        Date newStart = nextDay(previousEnd);

        private static Date nextDay(Date arg) {
            // foreign _03method, should be on date
            return new Date (arg.getYear(),arg.getMonth(), arg.getDate() + 1);
        }
    }

}
