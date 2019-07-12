package org.person.dfw.annotation.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;


//生成接口的处理类 ，在此不考虑方法的参数及返回类型(为了演示简单)
@SupportedAnnotationTypes("org.person.dfw.annotation.processor.GenerateInterface")
//@SupportedOptions({"name"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CreateInterfaceProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;

    private int r = 1;//轮循次数

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //初始化Filer和Messager
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        messager.printMessage(Kind.NOTE, "process() is execute...");

        //获取所有编译类元素，并打印，测试用
        Set<? extends Element> elements = roundEnv.getRootElements();
        System.out.println("输入的所有类有：");
        for (Element e : elements) {
            System.out.println(">>>>>> " + e.getSimpleName());
        }

        //获取使用了注解@GenerateInterface的类元素
        System.out.println("需要生成相应接口的类有：");
        Set<? extends Element> genElements = roundEnv.getElementsAnnotatedWith(GenerateInterface.class);
        for (Element e : genElements) {
            System.out.println(">>>>>>> " + e.getSimpleName());

            GenerateInterface gi = e.getAnnotation(GenerateInterface.class);
            String className = e.getSimpleName() + gi.suffix();
            String classString = "package com.zenfery.example.annotation.bean;\n"
                    + "public interface " + className + " {\n";
            //获取所有的方法元素
            List<? extends Element> genElementAlls = e.getEnclosedElements();
            System.out.println(">>>> 类" + e.getSimpleName() + "封装元素(仅对修饰符有public的生成接口方法):");
            for (Element e1 : genElementAlls) {
                System.out.println(">>> >>> " + e1.getSimpleName() + " 修饰符：" + e1.getModifiers());
                if (!e1.getSimpleName().toString().equals("<init>") && e1.asType() instanceof ExecutableType && isPublic(e1)) {
                    System.out.println(">>> >>> >>> " + e1.getSimpleName());
                    classString += " void " + e1.getSimpleName() + "();\n";
                }
            }
            classString += "}";
            System.out.println(classString);
            try {
                JavaFileObject jfo = filer.createSourceFile("com.zenfery.example.annotation.bean." + className, e);
                Writer writer = jfo.openWriter();
                writer.flush();
                writer.append(classString);
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("-------------------注解处理器第" + (r++) + "次循环处理结束...\n");
        return true;
    }

    //判断元素是否为public
    public boolean isPublic(Element e) {
        //获取元素的修饰符Modifier,注意此处的Modifier
        //非java.lang.reflect.Modifier
        Set<Modifier> modifiers = e.getModifiers();
        for (Modifier m : modifiers) {
            if (m.equals(Modifier.PUBLIC)) return true;
        }
        return false;
    }

}