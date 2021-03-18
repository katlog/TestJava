package name.katlog.httpclient;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by fw on 2019/8/8
 */
public class TestHttpClient_Basic {

    @Test
    public void uriBuilder() throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("q", "httpclient")
                .setParameter("btnG", "Google Search")
                .setParameter("aq", "f")
                .setParameter("oq", "")
                .build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

    }
    
    @Test
    public void response(){
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().getReasonPhrase());
        System.out.println(response.getStatusLine().toString());
    }

    @Test
    public void header(){
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
        response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);

    }

    @Test
    public void header2(){
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c3=a; path=/; domain=yeetrack.com");
        response.addHeader("Set-Cookie", "c4=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void header3(){
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a; path=/; domain=yeetrack.com");
        response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"yeetrack.com\"");
        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        while (it.hasNext()) {
            HeaderElement elem = it.nextElement();
            System.out.println(elem.getName() + " = " + elem.getValue());
            NameValuePair[] params = elem.getParameters();
            for (NameValuePair param : params) {
                System.out.println(" " + param);
            }
        }
    }

    @Test
    public void entity() throws IOException {
        StringEntity myEntity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
        System.out.println(myEntity.getContentType());
        System.out.println(myEntity.getContentLength());
        System.out.println(EntityUtils.toString(myEntity));
        System.out.println(EntityUtils.toByteArray(myEntity).length);

    }


}
