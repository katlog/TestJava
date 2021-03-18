package name.katlog.objectAndclass.innerclass;

public class Test1 {

    public static void main(String[] args) {
        Outter outter = new Outter();
        outter.new Inner().print();
    }
}

class Outter {
    private int a = 1;
    class Inner {
        private int a = 2;

        public void print() {
            int a = 3;
            System.out.println("局部变量：" + a);
            System.out.println("内部类变量：" + this.a);
            System.out.println("外部类变量：" + Outter.this.a);
        }
    }
}

class WithInner {
    class Inner {

    }
}

class InheritInner extends WithInner.Inner {

    // InheritInner() 是不能通过编译的，一定要加上形参
    InheritInner(WithInner wi) {
        wi.super(); // 必须有这句调用
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);
    }
}