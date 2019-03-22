package name.katlog.refactor._07movecharacter;

/**
 * Created by fw on 2018/4/19
 */
public class ExtractClass {

    class BeforeRefactor {
        class Person {

            public String getName() {
                return _name;
            }

            public String getTelephoneNumber() {
                return ("(" + _officeAreaCode + ") " + _officeNumber);
            }

            String getOfficeAreaCode() {
                return _officeAreaCode;
            }

            void setOfficeAreaCode(String arg) {
                _officeAreaCode = arg;
            }

            String getOfficeNumber() {
                return _officeNumber;
            }

            void setOfficeNumber(String arg) {
                _officeNumber = arg;
            }

            private String _name;
            private String _officeAreaCode;
            private String _officeNumber;

        }

    }

    class Refactor {
        class Person {

            public String getName() {
                return _name;
            }

            public String getTelephoneNumber() {
                return ("(" + _officeAreaCode + ") " + _officeNumber);
            }

            String getOfficeAreaCode() {
                return _officeAreaCode;
            }

            void setOfficeAreaCode(String arg) {
                _officeAreaCode = arg;
            }

            String getOfficeNumber() {
                return _officeNumber;
            }

            void setOfficeNumber(String arg) {
                _officeNumber = arg;
            }

            private String _name;
            private String _officeAreaCode;
            private String _officeNumber;

            private TelephoneNumber _officeTelephone = new TelephoneNumber();
        }

        class TelephoneNumber {}

    }

    class Refactor1 {
        class TelephoneNumber {
            String getAreaCode() {
                return _areaCode;
            }

            void setAreaCode(String arg) {
                _areaCode = arg;
            }

            private String _areaCode;
        }

        class Person {
            public String getTelephoneNumber() {
                return ("(" + getOfficeAreaCode() + ") " + _officeNumber);
            }

            String getOfficeAreaCode() {
                return _officeTelephone.getAreaCode();
            }

            void setOfficeAreaCode(String arg) {
                _officeTelephone.setAreaCode(arg);
            }

            private String _officeNumber;
            private TelephoneNumber _officeTelephone = new TelephoneNumber();
        }
    }

    class Refactor2 {
        class Person {
            public String getName() {
                return _name;
            }

            public String getTelephoneNumber() {
                return _officeTelephone.getTelephoneNumber();
            }

            TelephoneNumber getOfficeTelephone() {
                return _officeTelephone;
            }

            private String _name;
            private TelephoneNumber _officeTelephone = new TelephoneNumber();
        }

        class TelephoneNumber {
            public String getTelephoneNumber() {
                return ("(" + _areaCode + ") " + _number);
            }

            String getAreaCode() {
                return _areaCode;
            }

            void setAreaCode(String arg) {
                _areaCode = arg;
            }

            String getNumber() {
                return _number;
            }

            void setNumber(String arg) {
                _number = arg;
            }

            private String _number;
            private String _areaCode;
        }

    }
}
