
/**  
 * @Title: TitlePositionEditor.java
 * @Package: org.person.dfw.others.beans
 * @author: katlog
 * @date: 2017年5月4日 上午9:18:24
 * @version: V1.0  
 */ 
package org.person.dfw.others.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.beans.SimpleBeanInfo;

import javax.swing.JPanel;

public class TitlePositionEditor extends PropertyEditorSupport{
    
    private String[] options = {"left","center","right"};
    
    /**代表可选属性值的字符串标识数组*/
    public String[] getTags(){return options;}
    
    /**代表属性初始值的字符串*/
    public String getJavaInitializationString (){return ""+getValue();}
    
    /**将内部属性值转换成对应字符串表示形式，供属性编辑器使用*/
    public String getAsText(){
        int value = (int)getValue();
        return options[value];
    }
    
    /**将外部设置的字符串转换成内部属性的值*/
    public void setAsText(String s){
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(s)) {
                setValue(s);
                return;
            }
        }
    }
    
}

class ChartBean extends JPanel{
    private int titlePostion = (int) CENTER_ALIGNMENT;
    private boolean inverse;
}

class ChartBeanBeanInfo extends SimpleBeanInfo{
    public PropertyDescriptor[] getPropertyDescriptors(){
        try {
            PropertyDescriptor titlePositionDescriptor = new PropertyDescriptor("titlePosition", ChartBean.class);
            titlePositionDescriptor.setPropertyEditorClass(TitlePositionEditor.class);
            
            PropertyDescriptor inverseDescriptor = new PropertyDescriptor("inverse", ChartBean.class);
            inverseDescriptor.setPropertyEditorClass(TitlePositionEditor.class);
            
            return new PropertyDescriptor[]{titlePositionDescriptor,inverseDescriptor};
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
