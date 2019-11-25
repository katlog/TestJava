package lambdasinaction.chap14;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Combinators {

    final static String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " ché la  dritta via era   smarrita ";

    public static void main(String[] args) {
        System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);


    }

    static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    static <A> Function<A, A> repeat(int n, Function<A, A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {  //←─和迭代算法一样，accumulate 方法一个个遍历Character
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);    //←─上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {    //←─合并两个Word-Counter，把其计数器加起来
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);    //←─仅需要计数器的总和，无需关心lastSpace
    }

    public int getCounter() {
        return counter;
    }
}

class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));    //←─处理当前字符
        return currentChar < string.length();    //←─若还有字符要处理，则返回true
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;               //←─返回null表示要解析的String已经足够小，可顺序处理
        }
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length(); splitPos++) {    //←─将试探拆分位置设定为要解析的String的中间
            if (Character.isWhitespace(string.charAt(splitPos))) {    //←─让拆分位置前进直到下一个空格
                Spliterator<Character> spliterator =    //←─创建一个新WordCounter-Spliterator来解析String从开始到拆分位置的部分
                        new WordCounterSpliterator(string.substring(currentChar,
                                splitPos));
                currentChar = splitPos;    //←─将这个WordCounter-Spliterator 的起始位置设为拆分位置
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }


}
