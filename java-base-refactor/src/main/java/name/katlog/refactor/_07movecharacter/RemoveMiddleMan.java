package name.katlog.refactor._07movecharacter;

/**
 * Created by fw on 2018/4/19
 */
public class RemoveMiddleMan {

    class BeforeRefactor {
        class Person {
            Department _department;

            public void setDepartment(Department arg) {
                _department = arg;
            }

            public Person getManager() {
                return _department.getManager();
            }
        }

        class Department {
            private String _chargeCode;
            private Person _manager;

            public Department(Person manager) {
                _manager = manager;
            }

            public Person getManager() {
                return _manager;
            }

        }
    }






}
