package name.katlog.net;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class TestUrl {

    /**
     * url转成file
     */
    @Test
    public void url2File() throws URISyntaxException {
        URL resource = Thread.currentThread().getClass().getResource("/");

        File file = new File(resource.toURI());
        if (file.exists()) {
            System.out.println("file = " + file);
        }
    }
}
