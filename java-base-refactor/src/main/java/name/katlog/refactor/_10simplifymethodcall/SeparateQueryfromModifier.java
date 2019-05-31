package name.katlog.refactor._10simplifymethodcall;

/**
 * Created by fw on 2018/4/25
 * 将查询函数和修改函数分离
 */
public class SeparateQueryfromModifier {

    private void sendAlert(){
    }
    private void someLaterCode(String found) {

    }

    class BeforeRefator{

        String foundMiscreant(String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    sendAlert();
                    return "Don";
                }
                if (people[i].equals ("John")){
                    sendAlert();
                    return "John";
                }
            }
            return "";
        }


        /**  客户端代码 */
        void checkSecurity(String[] people) {
            String found = foundMiscreant(people);
            someLaterCode(found);
        }
    }


    class Refactor1{

        /**  为将查询动作和修改动作分开， 先建立一个适当的查询函数，使其与修改函数返回相同的值，但不造成任何副作用 */
        String foundPerson(String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    return "Don";
                }
                if (people[i].equals ("John")){
                    return "John";
                }
            }
            return "";
        }

        /**  然后，逐一替换原函数内所有的return句，改调用新建的查询函数。每次替换后，编译并测试 */
        String foundMiscreant(String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    sendAlert();
                    return foundPerson(people);
                } if (people[i].equals ("John")){
                    sendAlert();
                    return foundPerson(people);
                }
            }
            return foundPerson(people);
        }

        /**  客户端代码
         *    现在，修改调用者，将原本的单一调用动作替换为两个调用：先调用修改函数，然后调用查询函数
         * */
        void checkSecurity(String[] people) {
            foundMiscreant(people);
            String found = foundPerson(people);
            someLaterCode(found);
        }
    }

    class Refactor2{

        String foundPerson(String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    return "Don";
                }
                if (people[i].equals ("John")){
                    return "John";
                }
            }
            return "";
        }

        /**  所有调用都替换完毕后，就可将修改函数的返回值改为void */
        void foundMiscreant (String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    sendAlert();
                    return;
                }
                if (people[i].equals ("John")){
                    sendAlert();
                    return;
                }
            }
        }

        /**  现在，为原函数改个名称可能会更好一些： */
        void sendAlert1 (String[] people){
            for (int i = 0; i < people.length; i++) {
                if (people[i].equals ("Don")){
                    sendAlert();
                    return;
                }
                if (people[i].equals ("John")){
                    sendAlert();
                    return;
                }
            }
        }

        /**  现在可对修改函数实施Substitute Algorithm，设法让它再简洁一些 */
        void sendAlert2(String[] people){
            if (! foundPerson(people).equals(""))
                sendAlert();
        }
    }
}
