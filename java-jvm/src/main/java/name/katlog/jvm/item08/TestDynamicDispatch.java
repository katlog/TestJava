package name.katlog.jvm.item08;

/**
 * @moudle: TestDynamicDispatch
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年7月31日 上午9:33:14 体现的是重写：多个类相同签名的方法的选择
 */
public class TestDynamicDispatch {

	static abstract class Human {
		protected abstract void sayHello();
	}

	/*
	 * static class Human { //和采用抽象类Human的执行结果一样
	 * 
	 * protected void sayHello(){
	 * 
	 * System.out.println("Human say hello"); }; }
	 */
	static class Man extends Human {
		@Override protected void sayHello() {
			System.out.println("man say hello");
		}
	}

	static class Woman extends Human {
		@Override protected void sayHello() {
			System.out.println("woman say hello");
		}
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		man.sayHello();
		woman.sayHello();
		man = new Woman();
		man.sayHello();
	}
}
