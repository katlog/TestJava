package name.katlog.annotation.bean;

import name.katlog.annotation.processor.GenerateInterface;

//老师类
@GenerateInterface(suffix = "IntSuffix")
public class Teacher {

    //教书
    private void teach() {
        System.out.println("teach...");
    }

    //行走
    public void walk() {
        System.out.println("walking");
    }
}