package name.katlog.tool;

/**
 * Created by fw on 2019/4/4
 * 包装
 */
public class Box<D> {

    private D data;

    public Box(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public String toString() {
        return "Box{" + "data=" + data + '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
