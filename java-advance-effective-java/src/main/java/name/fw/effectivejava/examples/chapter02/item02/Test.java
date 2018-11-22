package name.fw.effectivejava.examples.chapter02.item02;




public class Test implements Comparable<Test>{
    
    public Object clone()
        throws CloneNotSupportedException {
        return super.clone();
    }
    
    
    public static void main(String[] args) {
        
    }

    @Override
    public int compareTo(Test o) {
        // TODO Auto-generated _03method stub
        return 0;
    }
}