package name.fw.effectivejava.examples.chapter04.item18;

/**
 * @moudle: ExtractMethod
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2018年1月28日 下午5:44:36
 *
 */
public class Demo {

}


class AudioClip{}
class Song{}
interface Singer{
    AudioClip sing(Song s);
}
interface Songwriter{
   Song compose(boolean hit);
}
interface SingerSongwirter extends Singer,Songwriter{
    AudioClip strum();
    void actSensitive();
}