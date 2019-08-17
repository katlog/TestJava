package name.katlog.jvm.item06;

public class ExceptionTableDemo {

    public int inc(){
        int x;
        try{
            x=1;
            return x;
        }catch(Exception e){
            x=2;
            return x;
        }finally{
            x=3;
        }
    }

    public int incV1(){
        int x;
        try{
            x=1;
            int i = x / 0;
            return x;
        }catch(Exception e){
            x=2;
            int i = x / 0;
            return x;
        }finally{
            x=3;
        }
    }

    public static void main(String[] args) {
        int inc = new ExceptionTableDemo().inc();
        // 返回 1
        System.out.println("inc = " + inc);
    }
}
