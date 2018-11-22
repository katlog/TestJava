package dfw.asm._01introduction;

public class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }

    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}