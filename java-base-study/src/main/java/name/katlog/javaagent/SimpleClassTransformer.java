package name.katlog.javaagent;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * Created by fw on 2021/1/6
 */
public class SimpleClassTransformer implements ClassFileTransformer {


    /**
     * 这里使用了java assist，也可用asm等
     *
     * */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        if (className.endsWith("sun/net/www/protocol/http/HttpURLConnection")) {
            ClassPool classPool = ClassPool.getDefault();
            CtClass clazz = null;
            try {
                clazz = classPool.get("sun.net.www.protocol.http.HttpURLConnection");

                CtConstructor[] cs = clazz.getConstructors();
                for (CtConstructor constructor : cs) {
                    // 在构造函数结束的位置插入如下的内容
                    constructor.insertAfter("System.out.println(this.getURL());");
                }

                byte[] byteCode = clazz.toBytecode();

                // 将类移出
                clazz.detach();

                return byteCode;
            } catch (NotFoundException | CannotCompileException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
