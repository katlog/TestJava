package org.person.dfw.util.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fw on 2019/9/26
 */
public final class PicUtil {
    private PicUtil() {
    }

   private static Pattern p = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)");

    public static boolean valid(String picUrl){
        if (picUrl == null || picUrl.trim().length() == 0) {
            return false;
        }
        return p.matcher(picUrl).matches();
    }
}
