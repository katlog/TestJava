// Bit field enumeration constants - OBSOLETE!
package name.fw.effectivejava.examples.chapter06.item32;


public class Text0 {
    
    public static final int STYLE_BOLD              = 1 <<0;
    public static final int STYLE_ITALIC            = 1 <<1;
    public static final int STYLE_UNDERLINE         = 1 <<2;
    public static final int STYLE_STRIKETHROUGH     = 1 <<3;
    
    // Parameter is bitwise OR of zero or more STYLE constans
    public void applyStyles(int styles) {
        
    }
    
    public static void main(String[] args) {
        new Text0().applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
	
}
