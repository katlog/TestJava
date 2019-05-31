package name.katlog.refactor._08reorganizedata;

/**
 * Created by fw on 2018/4/24
 * 封装值域
 *  class中存在一个public值域。将它声明为private，并提供相应的访问函数（accessors）
 */
public class EncapsulateField {

    class Demo{
        class Before{
            public String _name;
        }
        class Refactor{
            private String _name;
            public String getName() {return _name;}
            public void setName(String arg) {_name = arg;}
        }
    }


}
