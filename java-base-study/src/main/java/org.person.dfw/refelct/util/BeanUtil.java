/**
 * @Title: DtoCheckUtil.java
 * @Package: org.person.dfw.refelct
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月9日 下午6:08:01
 * @version: V1.0
 */
package org.person.dfw.refelct.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.collect.Lists;
import net.sf.json.JSONObject;

import org.junit.Test;

/**
 * @moudle: DtoCheckUtil
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月9日 下午6:08:01
 *
 */
public class BeanUtil {

    private static Map classMap = new HashMap();

    /**
     * 检查对象，除某些属性外是否为空
     * <p>Title: checkDtoEntire</p>
     * <p>author : fw</p>
     * <p>date : 2017年3月9日 下午5:41:38</p>
     * 
     * @param obj
     * @param exceptFileds
     * @return
     * @throws Exception
     */
    private <T> boolean checkDtoEntire(T obj, String... exceptFileds)
        throws Exception {
        Field[] allFields = obj.getClass().getDeclaredFields();
        List<String> allFieldName = new ArrayList<String>();
        JSONObject json = new JSONObject();

        for (Field field : allFields) {
            allFieldName.add(field.getName());
        }
        for (String exceptName : exceptFileds) {
            if (!allFieldName.contains(exceptName)) {
                throw new Exception("属性填写不对！");
            }
            allFieldName.remove(exceptName);
        }
        for (String name : allFieldName) {
            for (Field field : allFields) {
                if (field.getName().equals(name)) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (value == null || value.toString().equals("")) {
                        json.put("result", false);
                        json.put("lack", name);
                        return false;
                    }
                }
            }
        }
        return true;
    }



    /**
     * Title: getBeanProVal</br>
     * author : 丰伟</br>
     * date : 2017年7月19日 下午3:35:39</br>
     * @param pros 要选择的属性，默认选择全部值不为空的属性
     * @return map
     */ 
    public static <T> Map<String, Object> getProVal(T bean,String... pros){
    	Map<String, Object> map = new HashMap<>();
    	Field[] fields = bean.getClass().getDeclaredFields();
    	
    	//是否进行属性筛选
    	boolean isExlude = true;
    	List<String> proList =null;
    	if (pros==null||pros.length<1) {
			isExlude = false;
		}else {
			proList = Arrays.asList(pros);
		}
    	for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(bean);
				if (value==null) {
					continue;
				}else {
					//区分哪些属性放入map中
					boolean notContain = isExlude&&proList!=null&&!proList.contains(field.getName());
					if (notContain) {
						continue;
					}
					map.put(field.getName(), value);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
    	
		return map;
    }
    
    
    private static  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public  static <T> String comparePro(T before,T after,boolean include, String... strFields){
        
        if (before.getClass()!=after.getClass()) {
            return "";
        }
        boolean equal = false;
        StringBuffer beforeStr = new StringBuffer("{");
        StringBuffer afterStr = new StringBuffer("{");
        
        //查询包含的字段
        List<Field> fields = Arrays.asList(before.getClass().getDeclaredFields());
        List<Field> compareFields = new ArrayList<Field>();
        
        if (strFields==null) {
            compareFields = fields;
        }else {
            //包含
            if (include) {
                for (String fieldName : strFields) {
                    for (Field field : fields) {
                        if (field.getName().equals(fieldName)) {
                            compareFields.add(field);
                            break;
                        }
                    }
                }
            }else {
                //排除
                compareFields = fields;
                for (String fieldName : strFields) {
                    for (Field field : fields) {
                        if (field.getName().equals(fieldName)) {
                            compareFields.remove(field);
                            break;
                        }
                    }
                }
            }
        }
        
        for (Field field : compareFields) {
                try {
                    field.setAccessible(true);
                    Object beforeObj  = field.get(before);
                    Object afterObj = field.get(after);
                    
                    if (beforeObj==null&&afterObj==null) {
                        continue;
                    }else if (beforeObj==null&&afterObj!=null) {
                        beforeStr.append(field.getName()+":"+",");
                        afterStr.append(field.getName()+":"+afterObj+",");
                        equal = true;
                    }else if (afterObj==null&&beforeObj!=null) {
                        beforeStr.append(field.getName()+":"+beforeObj+",");
                        afterStr.append(field.getName()+":"+",");
                        equal = true;
                    }else if (beforeObj.equals(afterObj)) {
                        continue;
                    }else{
                        if ("Date".equals(beforeObj.getClass().getSimpleName())||"Timestamp".equals(beforeObj.getClass().getSimpleName())) {
                            String date1 = sdf.format(beforeObj);
                            String date2 = sdf.format(beforeObj);
                            if (!date1.equals(date2)) {
                                beforeStr.append(field.getName() + ":" + date1 + ",");
                                afterStr.append(field.getName() + ":" + date2 + ",");
                                equal = true;
                            }
                        } else {
                            beforeStr.append(field.getName() + ":" + beforeObj + ",");
                            afterStr.append(field.getName() + ":" + afterObj + ",");
                            equal = true;
                        }
                       
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                
        }
        if (!equal) {
            return "";
        }else {
            beforeStr.deleteCharAt(beforeStr.length()-1);
            afterStr.deleteCharAt(afterStr.length()-1);
            beforeStr.append("}");
            afterStr.append("}");
        }
        
        return "修改前"+beforeStr+"修改后"+afterStr;
    }
    
    
    /**
     * 比较两个bean的属性，返回不同
     * <p>Title: compareBeanProp</p>
     * <p>author : fw</p>
     * <p>date : 2017年3月20日 上午10:46:06</p>
     * @param before
     * @param after
     * @return
     */ 
    public  static <T> String comparePro(T before,T after){
       return comparePro(before, after, true, null);
    }
    
    
    /**
     * 获取对象的public static final 属性
     * Title: getPublicFinalStatictPro</br>
     * author : 丰伟</br>
     * date : 2017年8月11日 上午9:32:51</br>
     * @param o
     * @return map
     */
    public static <T> Map<String, Object> getPublicFinalStatictPro(T o){
    	
		Map<String, Object> map = new HashMap<String, Object>();
    	
    	Field[] fields = o.getClass().getDeclaredFields();
    	for (Field field : fields) {
    		int modifiers = field.getModifiers();
    		
			if (Modifier.isFinal(modifiers)&&Modifier.isStatic(modifiers)
					&&Modifier.isPublic(modifiers)) {
				try {
					map.put(field.getName(),field.get(o));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
    	return map;
    }
    
    /**获取对象的public static final 属性
     * Title: getPublicFinalStatictPro</br>
     * author : 丰伟</br>
     * date : 2017年8月11日 上午9:35:53</br>
     * @param cl 类
     * @return map
     * @throws InstantiationException
     * @throws IllegalAccessException
     */ 
    public static <T> Map<String, Object> getPublicFinalStatictPro(Class<T> cl) throws InstantiationException, IllegalAccessException{
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	//内部类
    	if (cl.isInterface()||cl.isMemberClass()) {
    		return getFileds(cl);
		}
		//接口
    	if (cl.isInterface()) {
			return getFileds(cl);
		}
    	
    	//没有 默认无参构造函数
    	boolean hasDefault = false;
    	Constructor<?>[] constructors = cl.getConstructors();
    	for (Constructor<?> constructor : constructors) {
			if (constructor.getParameterCount()==0) {
				hasDefault = true;
				break;
			}
		}
    	if (hasDefault) {
    		T instance = cl.newInstance();
    		return getPublicFinalStatictPro(instance);
		}else {
			return getFileds(cl);
		}
    	
    }



	private static <T> Map<String, Object> getFileds(Class<T> cl) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = cl.getDeclaredFields();
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if (Modifier.isFinal(modifiers)&&Modifier.isStatic(modifiers)
					&&Modifier.isPublic(modifiers)) {
				try {
					map.put(field.getName(),field.get(null));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

    /** 获取类的全部字段（含父类）
     * @param clazz
     * @return
     */
    public static List<Field> recursiveFields(Class clazz) {

        return clazz.getSuperclass() == null ? Lists.newArrayList(clazz.getDeclaredFields())
                :combine(Lists.newArrayList(clazz.getDeclaredFields()), recursiveFields(clazz.getSuperclass()));

    }

    private  static <T> List<T> combine(List<T> a,List<T> b) {
        List<T> result = new ArrayList(a);
        result.addAll(b);
        return result;
    }
    
    

    @Test
    public void testCompareBeanProp() {
        Dto dto1 = new Dto();
        Dto dto2 = new Dto();
        Dto dto3 = new Dto();
        Dto dto4 = new Dto();
        dto3.setBillAmt(100);
        dto4.setBillAmt(2000);
        dto3.setUserName("tom");

        Dto dto5 = new Dto();
        dto5.setBillDate("2017-12-12");
        dto5.setBillLastDate(new Date());
        Dto dto6 = new Dto();
        dto6.setBillDate("2017-12-11");
        dto6.setBillLastDate(Calendar.getInstance().getTime());
        String compareBeanProp1 = comparePro(dto1, dto2);
        String compareBeanProp2 = comparePro(dto3, dto4);
        String compareBeanProp3 = comparePro(dto5, dto6);
        String compareBeanProp4 = comparePro(dto5, dto6, true,"billDate");
        System.out.println(compareBeanProp1);
        System.out.println(compareBeanProp2);
        System.out.println(compareBeanProp3);
        System.out.println(compareBeanProp4);
    }
    
    @Test public void testGetProVal(){
        Dto dto3 = new Dto();
        Dto dto4 = new Dto();
        Object dto5 = new Dto();
        Object dto6 = new Dto("dto5Name",123);
        dto3.setBillAmt(100);
        dto4.setBillAmt(2000);
        dto3.setUserName("tom");
       System.out.println(getProVal(dto3, null));
       System.out.println(getProVal(dto4, null));
       System.out.println(getProVal(dto3, "userName"));
       System.out.println(getProVal(dto5));
       System.out.println(getProVal(dto5,null));
       System.out.println(getProVal(dto6,null));
       System.out.println(getProVal(dto6,"userName"));
    }
}
