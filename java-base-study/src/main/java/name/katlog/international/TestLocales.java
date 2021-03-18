package name.katlog.international;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import name.katlog.util.collections.ListUtil;
import name.katlog.util.Print;

/**
 * @moudle: TestLocales 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月16日 下午7:42:45
 *
 */
public class TestLocales {
	
	@Test public void _constructor(){

		//获取locale对象的方式
		Locale locale1 = new Locale("zh", "CN");				//带有语言和国家/地区信息的本地化对象  
		Locale locale2 = new Locale("zh");						//只有语言信息的本地化对象
		Locale locale3 = Locale.CHINA;							//等同locale1，Locale("zh", "CN")
		Locale locale4 = Locale.CHINESE;						//等同locale2，Locale("zh", "")
		Locale locale5 = Locale.getDefault();					//获取本地系统默认的本地化对象
		Locale locale6 = Locale.forLanguageTag("JP");
	}
	
	/**获取可用的locale对象*/
	@Test public void _avaliable(){
		
		Locale[] availableLocales1 = DateFormat.getAvailableLocales();
		ListUtil.format(Print.Style.Vertical, null, availableLocales1);
		
		Locale[] availableLocales2 = NumberFormat.getAvailableLocales();
		ListUtil.format(Print.Style.Vertical, null, availableLocales1);
		
		//验证两者获取的集合是否相同
		Set<Locale> locales1 = new HashSet<>(Arrays.asList(availableLocales1));			//用set除去重复元素
		locales1.addAll(Arrays.asList(availableLocales2));								//用set除去重复元素
		Assert.assertEquals(locales1.size(), availableLocales1.length);					//个数没发生变化
	}
	
	@Test public void _common(){
		
		Locale locale3 = Locale.CHINA;
		
		System.out.println(locale3.getDisplayCountry());						//返回在当前Locale中所表示的国家名
		System.out.println(locale3.getDisplayLanguage());						//返回在当前Locale中所表示的语言名称
		System.out.println(locale3.getDisplayName());							//返回一个在当前的Locale中所表示的用来描述Locale的名字
		System.out.println(locale3.getScript());
		System.out.println(locale3.getCountry());								//返回国家代码，它是由两个大写字母组成的ISO-3166代码
		
		Locale locale2 = Locale.JAPAN;
		
		System.out.println(locale3.getDisplayCountry(locale2));						//返回在当前Locale中所表示的国家名
		System.out.println(locale3.getDisplayLanguage(locale2));					//返回在当前Locale中所表示的语言名称
		System.out.println(locale3.getDisplayName(locale2));						//返回一个在当前的Locale中所表示的用来描述Locale的名字
		
	}
}
