package name.dfw.jvm.item08;

/**
 * @moudle: TestDispatch
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年7月31日 上午9:36:41
 *
 */
public class TestDispatch {

	static class QQ {}
	static class _360 {}

	public static class Father {
		public void hardChoice(QQ arg) {
			System.out.println("father choose qq");
		}
		public void hardChoice(_360 arg) {
			System.out.println("father choose 360");
		}
	}

	public static class Son extends Father {
		public void hardChoice(QQ arg) {
			System.out.println("son choose qq");
		}
		public void hardChoice(_360 arg) {
			System.out.println("son choose 360");
		}
	}

	public static void main(String[] args) {
		Father father = new Father();
		Father son = new Son();
		father.hardChoice(new _360());
		son.hardChoice(new QQ());
		
		System.out.println(System.currentTimeMillis());
	}
}
