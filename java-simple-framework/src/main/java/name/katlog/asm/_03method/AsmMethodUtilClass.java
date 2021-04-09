package name.katlog.asm._03method;

import org.junit.Test;
import org.objectweb.asm.util.ASMifier;

/**
 * Created by fw on 2021/4/9
 *
 *  org.objectweb.asm.commons 包中包含了一些预定义的方法适配器，可用于定义自己的适配器
 */
public class AsmMethodUtilClass {



    @Test
    public void ASMifier() throws Exception {

        ASMifier.main(new String[]{"java.lang.Runnable"});
        
    }
    
    
    @Test
    public void AnalyzerAdapter(){
        
    }
    
    @Test
    public void LocalVariablesSorter(){
        
    }
    
    @Test
    public void AdviceAdapter(){
        
    }
}
