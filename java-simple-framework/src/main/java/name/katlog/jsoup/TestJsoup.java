package name.katlog.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by fw on 2018/12/27
 */
public class TestJsoup {

    public TestJsoup() throws IOException {
    }

    /** -------------------------------------------输入操作---------------------------------------------- */
    @Test
    /** 基本使用 */
    public void basic() throws IOException {
        // 直接发送HTTP请求获得
        Document doc = Jsoup.connect("http://www.javacui.com/").get();
        Element content = doc.getElementById("left");
        // 根据ID获得文章列表DIV
        Elements elements = content.getElementsByTag("dl");
        // DIV中每个dl都是一篇文章
        int i = 1;
        // 用于显示第几篇文章
        for (Element e : elements) {
            Elements as = e.getElementsByAttribute("target");
            // 根据属性获取，一遍文章中有两个a，都有target属性
            //第一个是标题连接，第二个是点击详细
            Element a = as.get(0);
            // 获取第一个，既标题连接
            String title = a.text();
            // 问的标签内的文本
            System.out.println("第【" + i + "】篇文章标题：" + title);
            i++;
        }

    }

    @Test
    /** 使用connect */
    public void connect() throws IOException {
        Document doc = Jsoup.connect("http://example.com")
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
    }

    @Test
    /** 解析html字符串 */
    public void parse() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        String title = doc.title();
        System.out.println("title = " + title);
    }

    @Test
    /** 解析body片段 */
    public void parseBodyFragment() {
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc = Jsoup.parseBodyFragment(html);
        Element body = doc.body();
        System.out.println("body = " + body);
    }

    @Test
    /** 根据文件加载Document对象 */
    public void parseFile() throws IOException {
        File input = new File(this.getClass().getResource("katlog.html").getFile());
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        System.out.println("doc = " + doc);
    }

    /** -------------------------------------------输入操作---------------------------------------------- */
    /** -------------------------------------------数据抽取---------------------------------------------- */
    @Test
    /** 用dom方法遍历Document对象 */
    public void domIter() throws IOException {
        File input = new File(this.getClass().getResource("katlog.html").getFile());
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            System.out.println("linkHref = " + linkHref);
            String linkText = link.text();
            System.out.println("linkText = " + linkText);
        }
    }


    @Test
    /** 用元素选择器查找 */
    public void select() throws IOException {
    File input = new File(this.getClass().getResource("katlog.html").getFile());
    Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        //带有href属性的a元素
        Elements links = doc.select("a[href]");
        System.out.println("links = " + links);
        //扩展名为.png的图片
        Elements pngs = doc.select("img[src$=.png]");
        System.out.println("pngs = " + pngs);
        //class等于masthead的div标签
        Element masthead = doc.select("div.masthead").first();
        System.out.println("masthead = " + masthead);
        //在h3元素之后的a元素
        Elements resultLinks = doc.select("h3.r > a");
        System.out.println("resultLinks = " + resultLinks);
    }

    @Test
    /** 从元素集合抽取属性、文本和html内容 */
    public void extract(){
        String html = "<p>An <a href='http://example.com/'><b>example</b></a> link.</p>";
        //解析HTML字符串返回一个Document实现
        Document doc = Jsoup.parse(html);
        //查找第一个a元素
        Element link = doc.select("a").first();

        // "An example link"//取得字符串中的文本
        String text = doc.body().text();
        // "http://example.com/"//取得链接地址
        String linkHref = link.attr("href");
        // "example""//取得链接地址中的文本
        String linkText = link.text();

        String linkOuterH = link.outerHtml();
        // "<a href="http://example.com"><b>example</b></a>"
        // "<b>example</b>"//取得链接内的html内容
        String linkInnerH = link.html();
    }

    /** -------------------------------------------数据抽取---------------------------------------------- */

    /** -------------------------------------------修改数据---------------------------------------------- */
    @Test
    /** 用Element中的HTML设置方法 */
    public void elementSetHtml(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.<span>One</span></p><div></div></body></html>";
        Document doc = Jsoup.parse(html);

        // <div></div>
        Element div = doc.select("div").first();
        // <div><p>lorem ipsum</p></div>
        div.html("<p>lorem ipsum</p>");
        //在div前添加html内容
        div.prepend("<p>First</p>");
        //在div之后添加html内容
        div.append("<p>Last</p>");
        // 添完后的结果: <div><p>First</p><p>lorem ipsum</p><p>Last</p></div>
        System.out.println("div = " + div);

        // <span>One</span>
        Element span = doc.select("span").first();
        // 添完后的结果: <li><a href="http://example.com"><span>One</span></a></li>
        span.wrap("<li><a href='http://example.com/'></a></li>");
        System.out.println("span = " + span);
    }

    @Test
    public void elementSet(){

        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.<span>One</span></p><div></div></body></html>";
        Document doc = Jsoup.parse(html);


        // <div></div>
        Element div = doc.select("div").first();
        // <div>five &gt; four</div>
        div.text("five > four");
        div.prepend("First ");
        div.append(" Last");
        // now: <div>First five &gt; four Last</div>

        System.out.println("div = " + div);
    }
    /** -------------------------------------------修改数据---------------------------------------------- */


    /** -------------------------------------------html清理---------------------------------------------- */
    @Test
    public void htmlClean(){
        String unsafe =
                "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());



        // now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
        System.out.println("safe = " + safe);
    }
    /** -------------------------------------------html清理---------------------------------------------- */


    @Test
    public void  getMagent() throws IOException {

        // 直接发送HTTP请求获得
        Document doc = Jsoup.connect("https://www.kan84.tv/bdhd/bttokyohot.html").get();
        Elements downurl = doc.getElementsByClass("downurl");

        Element element = downurl.get(0);
        for (Element child : element.children()) {
            String href = child.child(0).attr("href");
            System.out.println( href);
        }
    }

}
