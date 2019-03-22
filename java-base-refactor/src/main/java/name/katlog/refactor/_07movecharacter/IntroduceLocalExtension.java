package name.katlog.refactor._07movecharacter;

import java.util.Date;

/**
 * Created by fw on 2018/4/24
 * 引入本地扩展
 */
public class IntroduceLocalExtension {

   static class BeforeRefactor {

        static class Client{
            private static Date nextDay(Date arg) {
                // foreign _03method, should be on date
                return new Date (arg.getYear(),arg.getMonth(), arg.getDate() + 1);
            }
        }
    }


        // 本地扩展方式一：subclassing
  class RefactorSub{

        class MfDateSub extends Date{
            public MfDateSub (String dateString) {
                super (dateString);
            }
            public MfDateSub (Date arg) {
                super (arg.getTime());
            }
            Date nextDay() {
                return new Date (getYear(),getMonth(), getDate() + 1);
            }
        }
    }

        // 本地扩展方式一：用上委托（delegation）
    static class RefactorWrapp{
        class MfDateWrap{
            private Date _original;
            public MfDateWrap (String dateString) {
                _original = new Date(dateString);
            };
            public MfDateWrap (Date arg) {
                _original = arg;
            }
            public int getYear() {
                return _original.getYear();
            }

            public int getMonth() {
                return _original.getMonth();
            }
            public int getDate() {
                return _original.getDate();
            }

            public boolean equals (MfDateWrap arg) {
                return (toDate().equals(arg.toDate()));
            }
            private Date toDate() {
                return _original;
            }

            Date nextDay() {
                return new Date (getYear(),getMonth(), getDate() + 1);
            }
        }
    }
}