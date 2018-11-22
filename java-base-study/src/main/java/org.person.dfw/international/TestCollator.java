package org.person.dfw.international;

import java.text.Collator;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;

import org.junit.Test;
import org.person.dfw.util.Print.Style;
import org.person.dfw.util.collections.ListUtil;


/**
 * 字符串特殊排序器
 * @moudle: TestCollator 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月16日 下午8:55:14
 *
 */
public class TestCollator {
	

	
	/**排序强度*/
	@Test public void _sort_strength(){
		
		class T {			//局部类的新奇使用方式啊，不屑于使用外部类的private方法进行隐藏，逻辑上更适合在同一个方法体z中
			public void _sort_strength_print(String[] strings, Collator collator) {
				Arrays.sort(strings, collator);
				ListUtil.format(Style.Vertical, null, strings);
				System.out.println("a equals b -> " + (collator.compare("a", "b") == 0 ? "true" : "false"));
				System.out.println("a equals à -> " + (collator.compare("a", "à") == 0 ? "true" : "false"));
				System.out.println("A equals a -> " + (collator.compare("a", "A") == 0 ? "true" : "false"));
			}
		}
		
		String[] strings = new String[]{"Zulu","America","able","zebra","Ångström"};
		
		Collator collator = Collator.getInstance();
		System.out.println(collator.getStrength());									//默认排序强度：TERTIARY 2
		
		collator.setStrength(Collator.PRIMARY);										//排序强度：PRIMARY 0
		new T()._sort_strength_print(strings, collator);  
		
		collator.setStrength(Collator.SECONDARY);									//排序强度：SECONDARY 1
		new T()._sort_strength_print(strings, collator);  
		
		collator.setStrength(Collator.TERTIARY);									//默认排序强度：TERTIARY 2		第三的
		new T()._sort_strength_print(strings, collator);  
		
		collator.setStrength(Collator.IDENTICAL);									//排序强度：IDENTICAL 3		完全相同的
		new T()._sort_strength_print(strings, collator);  
		
	}

	
	/**范化程度*/
	@Test public void _normalize_level(){
//		String a = "Ångström";
//		Collator collator = Collator.getInstance();
//		collator.setDecomposition(Collator.NO_DECOMPOSITION);
//		Collator.CANONICAL_DECOMPOSITION;
//		CollationKey aKey = collator.getCollationKey(a);
//		System.out.println(aKey.getSourceString());
////		if(aKey.compareTo(collator.getCollationKey(b)) == 0) // fast comparison
	}
	
	/**范化：4种范化形式：D、KD、C和KC*/
	@Test public void _normalize(){
		String string = "Ångström™";
		System.out.println(Normalizer.normalize(string, Form.NFC));					//C范化
		System.out.println(Normalizer.normalize(string, Form.NFD));					//D范化:CANONICAL_DECOMPOSITION,重音字符被分解为基字符和组合重音符
		System.out.println(Normalizer.normalize(string, Form.NFKC));				//KC范化
		System.out.println(Normalizer.normalize(string, Form.NFKD));				//KD范化:完全分解
	}
}
