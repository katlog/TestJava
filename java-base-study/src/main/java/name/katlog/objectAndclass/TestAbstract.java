package name.katlog.objectAndclass;

/**
 * @moudle: TestAbstract 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月16日 上午9:58:02
 *
 */
public class TestAbstract {
	
	abstract class A{
		abstract public void Ado1();
		abstract public void Ado2();
	}
	
	/**	子类中实现或部分实现抽象方法时，子类也要标记为抽象类*/
	abstract class A1 extends A{
		
	}
	/**	子类中实现或部分实现抽象方法时，子类也要标记为抽象类*/
	abstract class A2 extends A{
		@Override
		public void Ado1() {
		}
	}
	/**	子类中全部实现抽象方法时，可以不再是抽象类了(子类不含抽象方法也可以是抽象类)*/
	class A3 extends A{
		@Override
		public void Ado1() {
		}
		@Override
		public void Ado2(){
			
		}
	}
	/**	子类中全部实现抽象方法时，可以不再是抽象类了(子类不含抽象方法也可以是抽象类)*/
	abstract class A4 extends A{
		@Override
		public void Ado1() {
		}
		@Override
		public void Ado2(){
			
		}
	}
	
}
