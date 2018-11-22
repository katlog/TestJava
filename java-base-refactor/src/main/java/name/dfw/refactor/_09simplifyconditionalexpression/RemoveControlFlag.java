package name.dfw.refactor._09simplifyconditionalexpression;

/**
 * Created by fw on 2018/4/20
 * 移除控制标记
 */
public class RemoveControlFlag {

    class Demo {
        void checkSecurity(String[] people) {
            boolean found = false;
            for (int i = 0; i < people.length; i++) {
                if (!found) {
                    if (people[i].equals("Don")) {
                        sendAlert();
                        found = true;
                    }
                    if (people[i].equals("John")) {
                        sendAlert();
                        found = true;
                    }
                }
            }
        }

        private void sendAlert() {
            // send alert
        }
    }

    class DemoRefactor {
        void checkSecurity(String[] people) {
            boolean found = false;
            for (int i = 0; i < people.length; i++) {
                if (!found) {
                    if (people[i].equals("Don")) {
                        sendAlert();
                        break;
                    }
                    if (people[i].equals("John")) {
                        sendAlert();
                        found = true;
                    }
                }
            }
        }

        private void sendAlert() {
            // send alert
        }
    }

    class DemoRefactor1 {
        void checkSecurity(String[] people) {
            boolean found = false;
            for (int i = 0; i < people.length; i++) {
                if (!found) {
                    if (people[i].equals("Don")) {
                        sendAlert();
                        break;
                    }
                    if (people[i].equals("John")) {
                        sendAlert();
                        break;
                    }
                }
            }
        }

        private void sendAlert() {
            // send alert
        }
    }

    class DemoRefactor2 {
        void checkSecurity(String[] people) {
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals("Don")) {
                    sendAlert();
                    break;
                }
                if (people[i].equals("John")) {
                    sendAlert();
                    break;
                }
            }
        }

        private void sendAlert() {
            // send alert
        }
    }

    class Demo1 {
        void checkSecurity(String[] people) {
            String found = "";
            for (int i = 0; i < people.length; i++) {
                if (found.equals("")) {
                    if (people[i].equals("Don")) {
                        sendAlert();
                        found = "Don";
                    }
                    if (people[i].equals("John")) {
                        sendAlert();
                        found = "John";
                    }
                }
            }
            someLaterCode(found);
        }

        private void someLaterCode(String found) {

        }

        private void sendAlert() {
            // send alert
        }
    }

    class Demo1Refactor {
        void checkSecurity(String[] people) {
            String found = foundMiscreant(people);
            someLaterCode(found);
        }

        String foundMiscreant(String[] people) {
            String found = "";
            for (int i = 0; i < people.length; i++) {
                if (found.equals("")) {
                    if (people[i].equals("Don")) {
                        sendAlert();
                        found = "Don";
                    }
                    if (people[i].equals("John")) {
                        sendAlert();
                        found = "John";
                    }
                }
            }
            return found;
        }
        private void someLaterCode(String found) {
        }

        private void sendAlert() {
            // send alert
        }
    }
    class Demo1Refactor1 {
        void checkSecurity(String[] people) {
            String found = foundMiscreant(people);
            someLaterCode(found);
        }

        String foundMiscreant(String[] people){
            String found = "";
            for (int i = 0; i < people.length; i++) {
                if (found.equals("")) {if (people[i].equals ("Don")){
                    sendAlert();
                    return "Don";
                } if (people[i].equals ("John")){
                    sendAlert();
                    found = "John";
                }
                }
            } return found;
        }

        private void someLaterCode(String found) {
        }

        private void sendAlert() {
            // send alert
        }
    }
    class Demo1Refactor2 {
        void checkSecurity(String[] people) {
            String found = foundMiscreant(people);
            someLaterCode(found);
        }

        String foundMiscreant(String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    sendAlert();
                    return "Don";
                } if (people[i].equals ("John")){
                    sendAlert();
                    return "John";
                }
            } return "";
        }

        private void someLaterCode(String found) {
        }

        private void sendAlert() {
            // send alert
        }
    }

}
