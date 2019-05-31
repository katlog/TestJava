package name.katlog.refactor._08reorganizedata;

/**
 * Created by fw on 2018/4/24
 * 将引用对象改为值对象
 */
public class ChangeReferencetoValue {

    class Before {
        class Currency {
            private String _code;

            public String getCode() {
                return _code;
            }

            private Currency(String code) {
                _code = code;
            }

            public boolean equals(Object arg) {
                if (!(arg instanceof Currency)) return false;
                Currency other = (Currency) arg;
                return (_code.equals(other._code));
            }

            public int hashCode() {
                return _code.hashCode();
            }
        }
    }

}
