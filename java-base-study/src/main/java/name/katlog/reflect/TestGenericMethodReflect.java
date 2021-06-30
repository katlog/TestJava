package name.katlog.reflect;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TestGenericMethodReflect {
    /**
     * 方法一
     * @param map
     * @param list
     */
    public static void test01(Map<String,User>map,List<User>list){
        System.out.println("TestGenericMethodReflect.test01()");
    }
    /**
     * 方法二
     * @return
     */
    public Map<Integer,User>test02(){
        System.out.println("TestGenericMethodReflect.test02()");
        return null;
    }
    /**
     * 通过反射机制获取泛型
     * #java.util.Map<java.lang.String, com.dasenlin.reflectionconstractor.User>
     * 泛型类型class java.lang.String
     * 泛型类型class com.dasenlin.reflectionconstractor.User
     * #java.util.List<com.dasenlin.reflectionconstractor.User>
     * 泛型类型class com.dasenlin.reflectionconstractor.User
     * 返回值，泛型类型class java.lang.Integer
     * 返回值，泛型类型class com.dasenlin.reflectionconstractor.User
     * @param args
     */
    public static void main(String[] args) {
        try {

            Method m = TestGenericMethodReflect.class.getMethod("test01", Map.class,List.class);
            Type [] t = m.getGenericParameterTypes();//获取参数泛型
            for(Type paramType:t){
                System.out.println("#"+paramType);
                if(paramType instanceof ParameterizedType){
                    Type[]genericTypes = ((ParameterizedType)paramType).getActualTypeArguments();
                    for(Type genericType:genericTypes){
                        System.out.println("泛型类型"+genericType);
                    }
                }
            }

            Method m2 =TestGenericMethodReflect.class.getMethod("test02", null);
            Type returnType = m2.getGenericReturnType();//获取返回类型的泛型
            if(returnType instanceof ParameterizedType){
                Type [] genericTypes2 =((ParameterizedType)returnType).getActualTypeArguments();
                for(Type genericType2:genericTypes2){
                    System.out.println("返回值，泛型类型"+genericType2);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}