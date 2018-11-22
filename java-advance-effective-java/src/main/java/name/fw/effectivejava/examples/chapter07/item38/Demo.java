package name.fw.effectivejava.examples.chapter07.item38;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年1月18日 下午7:48:25
 *
 */
public class Demo {

    // Private helper function for a recursive sort
    public static void sort(long[] a,int offset,int length) {
        assert a!=null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length -offset;
        //... // Do the computation
    }
}
