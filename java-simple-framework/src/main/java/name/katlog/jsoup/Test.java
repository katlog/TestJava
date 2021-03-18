package name.katlog.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by fw on 2021/1/9
 */
public class Test {

    public static void main(String[] args) throws IOException {


        Document doc = Jsoup.connect("https://time.geekbang.org/serv/v1/article")
                // .data("query", "Java")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36")
                .header("Host","time.geekbang.org")
                .cookie("auth", "token")
                .cookie("_ga","GA1.2.925747678.1586832633")
                .cookie("LF_ID","1586832633001-4105476-1762437")
                .cookie("GCID","a4de720-305cee6-6c2c44c-24e95f2")
                .cookie("GRID","a4de720-305cee6-6c2c44c-24e95f2")
                .cookie("_gid","GA1.2.1472440418.1609832743")
                .cookie("gksskpitn","0e4300e9-5622-4af6-bf30-1c57ff09886d")
                .cookie("GCESS","BQIEQxj5XwgBAwkBAQoEAAAAAAQEAC8NAAUEAAAAAAcElOPZeAEIgnIZAAAAAAALAgUAAwRDGPlfBgS9XiCmDAEB")
                .cookie("Hm_lvt_59c4ff31a9ee6263811b23eb921a5083","1609727189,1610095521,1610101640,1610160150")
                .cookie("sensorsdata2015jssdkcross","%7B%22distinct_id%22%3A%221667714%22%2C%22first_id%22%3A%22173d745887327a-065349df188979-376b4502-921600-173d745887479a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Faccount.infoq.cn%2Fsyncinfoq%2F%3Fto%3D0d6cfe96dded993b%26redirect%3Dhttps%253A%252F%252Ftime.geekbang.org%252Fcolumn%252Fintro%252F250%22%2C%22%24latest_landing_page%22%3A%22https%3A%2F%2Ftime.geekbang.org%2Fcolumn%2Fintro%2F250%22%2C%22%24latest_utm_source%22%3A%22baidu-ad%22%2C%22%24latest_utm_medium%22%3A%22ppzq-pc%22%2C%22%24latest_utm_term%22%3A%22baidu-ad-ppzq-title%22%2C%22%24latest_utm_campaign%22%3A%22guanwang%22%2C%22%24latest_utm_content%22%3A%22title%22%7D%2C%22%24device_id%22%3A%22173d745887327a-065349df188979-376b4502-921600-173d745887479a%22%7D")
                .cookie("SERVERID","3431a294a18c59fc8f5805662e2bd51e|1610160219|1610160177")
                .cookie("_gat","1")
                .cookie("Hm_lpvt_59c4ff31a9ee6263811b23eb921a5083","1610160274")
                .cookie("gk_process_ev","{%22count%22:7%2C%22utime%22:1610160271816%2C%22referrer%22:%22https://time.geekbang.org/column/intro/250%22%2C%22target%22:%22page_course_article_detail%22%2C%22referrerTarget%22:%22page_course_article_detail%22}")
                .timeout(3000)
                .data("id","185684")
                .data("include_neighbors","true")
                .data("is_freelyread","true")
                .post();

        System.out.println("doc = " + doc);


    }
}
