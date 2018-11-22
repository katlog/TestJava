package name.dfw.refactor._07movecharacter;

/**
 * Created by fw on 2018/4/24
 */
public class InlineClass {

    class BeforeRefactor {
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


    class Refactor {
        class Person {
            String getAreaCode() {
                return _officeTelephone.getAreaCode(); //译注： 请注意其变化
            }

            void setAreaCode(String arg) {
                _officeTelephone.setAreaCode(arg); //译注： 请注意其变化
            }

            String getNumber() {
                return _officeTelephone.getNumber(); //译注： 请注意其变化
            }

            void setNumber(String arg) {
                _officeTelephone.setNumber(arg); //译注： 请注意其变化
            }

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
