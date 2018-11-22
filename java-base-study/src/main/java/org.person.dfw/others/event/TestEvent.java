package org.person.dfw.others.event;
import java.util.EventListener;  
import java.util.EventObject;  
import java.util.HashSet;  
import java.util.Iterator;  
import java.util.Set;  

/**java事件机制包括三个部分：事件、事件监听器、事件源*/
public class TestEvent {
    public static void main(String[] args) {  
        EventSourceObject object = new EventSourceObject();  
        //注册监听器  
        object.addCusListener(new CusEventListener(){  
            @Override  
            public void fireCusEvent(CusEvent e) {  
                super.fireCusEvent(e);  
            }  
        });  
        //触发事件  
        object.setName("eric");  
    } 
}

/**事件类,用于封装事件源及一些与事件相关的参数. */
class CusEvent extends EventObject {  
    private static final long serialVersionUID = 1L;  
    private Object source;//事件源  
    public CusEvent(Object source){  
        super(source);  
        this.source = source;  
    }  
    public Object getSource() {  
        return source;  
    }  
    public void setSource(Object source) {  
        this.source = source;  
    }  
}

/** 
 * 事件监听器，实现java.util.EventListener接口。定义回调方法，将你想要做的事 
 * 放到这个方法下,因为事件源发生相应的事件时会调用这个方法。 
 */  
class CusEventListener implements EventListener {  
    //监听某一具体的事件，此处为CusEvent事件，方法内为事件发生后的回调方法  
    public void fireCusEvent(CusEvent e){  
    	EventSourceObject eObject= (EventSourceObject)e.getSource();  
        System.out.println("My name has been changed!");  
        System.out.println("I got a new name,named \""+eObject.getName()+"\"");
   }  
}

/**事件源.事件发生的地方，由于事件源的某项属性或状态发生了改变(比如BUTTON被单击、TEXTBOX的值发生改变等等)
 * 导致某项事件发生。换句话说就是生成了相应的事件对象。
 * 因为事件监听器要注册在事件源上,所以事件源类中应该要有盛装监听器的容器(List,Set等等)*/  
class EventSourceObject {  
    private String name;  
    //监听器容器  
    private Set<CusEventListener> listeners;  
    public EventSourceObject(){  
        this.listeners = new HashSet<CusEventListener>();  
        this.name = "defaultname";  
    }  
    //给事件源注册监听器  
    public void addCusListener(CusEventListener cel){  
        this.listeners.add(cel);  
    }  
   
    public String getName() {  
        return name;  
    }  
    //模拟事件触发器，当成员变量name的值发生变化时，触发事件。  
    public void setName(String name) {  
        if(!this.name.equals(name)){  
            this.name = name;  
            //当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）  
            for (CusEventListener listener: listeners) {
				listener.fireCusEvent(new CusEvent(this));
			}
        }        
    }  
}  