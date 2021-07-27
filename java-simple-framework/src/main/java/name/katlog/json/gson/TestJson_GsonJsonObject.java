package name.katlog.json.gson;

import com.google.gson.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by fw on 2021/7/14
 */
public class TestJson_GsonJsonObject {

    private static final String origin = "{\n" +
            "    \"status\":{\n" +
            "        \"code\":100,\n" +
            "        \"desc\":\"成功\",\n" +
            "        \"tipFileds\":\"\"\n" +
            "    },\n" +
            "    \"result\":{\n" +
            "        \"page\":{\n" +
            "            \"pageInfo\":{\n" +
            "                \"currentPageNum\":1,\n" +
            "                \"pageAmount\":7,\n" +
            "                \"rowsAmount\":67,\n" +
            "                \"rowsPerPage\":10\n" +
            "            },\n" +
            "            \"pageList\":[\n" +
            "                {\n" +
            "                    \"id\":100,\n" +
            "                    \"clusterName\":\"duanzu_scf_tzumc\",\n" +
            "                    \"orgName\":\"同镇质量效率部\",\n" +
            "                    \"orgId\":\"2017070321420000f1c37a\",\n" +
            "                    \"businessId\":29,\n" +
            "                    \"description\":\"同镇用户管理服务\",\n" +
            "                    \"createTime\":\"Oct 19, 2017 2:46:35 PM\",\n" +
            "                    \"hasAuth\":true,\n" +
            "                    \"image\":{\n" +
            "                        \"id\":100,\n" +
            "                        \"imageName\":\"duanzu_scf_tzumc\",\n" +
            "                        \"clusterId\":100,\n" +
            "                        \"description\":\"同镇用户管理服务\",\n" +
            "                        \"createTime\":\"Apr 27, 2018 2:59:45 PM\",\n" +
            "                        \"updateTime\":\"Jun 17, 2021 4:21:36 PM\",\n" +
            "                        \"lastVersion\":127\n" +
            "                    },\n" +
            "                    \"templateType\":\"IWORK\",\n" +
            "                    \"condition\":false,\n" +
            "                    \"priority\":\"低\",\n" +
            "                    \"httpMeshOption\":0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":635,\n" +
            "                    \"clusterName\":\"duanzu_scf_tzrecom\",\n" +
            "                    \"orgName\":\"同镇后端技术部\",\n" +
            "                    \"orgId\":\"20170703214200523e82f7\",\n" +
            "                    \"businessId\":29,\n" +
            "                    \"description\":\"同镇推荐服务\",\n" +
            "                    \"createTime\":\"Jul 2, 2018 12:07:20 PM\",\n" +
            "                    \"hasAuth\":true,\n" +
            "                    \"image\":{\n" +
            "                        \"id\":635,\n" +
            "                        \"imageName\":\"duanzu_scf_tzrecom\",\n" +
            "                        \"clusterId\":635,\n" +
            "                        \"createTime\":\"Jul 2, 2018 12:07:20 PM\",\n" +
            "                        \"updateTime\":\"Mar 25, 2021 11:13:00 AM\",\n" +
            "                        \"lastVersion\":50\n" +
            "                    },\n" +
            "                    \"templateType\":\"IWORK\",\n" +
            "                    \"condition\":false,\n" +
            "                    \"priority\":\"低\",\n" +
            "                    \"httpMeshOption\":0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":642,\n" +
            "                    \"clusterName\":\"duanzu_scf_tzactivity\",\n" +
            "                    \"orgName\":\"同镇后端技术部\",\n" +
            "                    \"orgId\":\"20170703214200523e82f7\",\n" +
            "                    \"businessId\":29,\n" +
            "                    \"description\":\"激励系统\",\n" +
            "                    \"createTime\":\"Jul 2, 2018 6:24:13 PM\",\n" +
            "                    \"hasAuth\":true,\n" +
            "                    \"image\":{\n" +
            "                        \"id\":642,\n" +
            "                        \"imageName\":\"duanzu_scf_tzactivity\",\n" +
            "                        \"clusterId\":642,\n" +
            "                        \"createTime\":\"Jul 2, 2018 6:24:13 PM\",\n" +
            "                        \"updateTime\":\"Oct 12, 2020 4:51:36 PM\",\n" +
            "                        \"lastVersion\":12\n" +
            "                    },\n" +
            "                    \"templateType\":\"IWORK\",\n" +
            "                    \"condition\":false,\n" +
            "                    \"priority\":\"低\",\n" +
            "                    \"httpMeshOption\":0\n" +
            "                },\n" +
            "                {\n" +
            "                    \"id\":797,\n" +
            "                    \"clusterName\":\"duanzu_scf_tztag\",\n" +
            "                    \"orgName\":\"同镇后端技术部\",\n" +
            "                    \"orgId\":\"20170703214200523e82f7\",\n" +
            "                    \"businessId\":29,\n" +
            "                    \"description\":\"交友标签服务\",\n" +
            "                    \"createTime\":\"Jul 17, 2018 11:48:51 AM\",\n" +
            "                    \"hasAuth\":true,\n" +
            "                    \"image\":{\n" +
            "                        \"id\":797,\n" +
            "                        \"imageName\":\"duanzu_scf_tztag\",\n" +
            "                        \"clusterId\":797,\n" +
            "                        \"createTime\":\"Jul 17, 2018 11:48:52 AM\",\n" +
            "                        \"updateTime\":\"Oct 9, 2020 5:46:24 PM\",\n" +
            "                        \"lastVersion\":18\n" +
            "                    },\n" +
            "                    \"templateType\":\"IWORK\",\n" +
            "                    \"condition\":false,\n" +
            "                    \"priority\":\"低\",\n" +
            "                    \"httpMeshOption\":0\n" +
            "                }\n" +
            "           ]\n" +
            "        }    \n" +
            "    }\n" +
            "}";

    private static final Gson gson = new GsonBuilder().create();
    private static final Gson pretty = new GsonBuilder().setPrettyPrinting().create();

    private static final JsonParser jsonParser = new JsonParser();

    JsonObject jsonObject = gson.fromJson(origin, JsonObject.class);

    /** json字符串转JsonObject */
    @Test
    public void fromJson(){
        jsonObject = gson.fromJson(origin, JsonObject.class);
        assertNotNull(jsonObject);
    }

    @Test
    public void getAsJsonObject(){
        assertNotNull(jsonObject);

        JsonObject result = jsonObject.getAsJsonObject("result");
        assertNotNull(result);
    }

    /** json里的元素 */
    @Test
    public void getElement(){

        JsonObject status = jsonObject.getAsJsonObject("status");
        JsonElement code = status.get("code");
        int asInt = code.getAsInt();
        assertEquals(100, asInt);

    }

    /** 构造普通jsonObject */
    @Test
    public void _constructJsonObject(){
        JsonObject jo = new JsonObject();
        jo.addProperty("name","katlog");
        jo.addProperty("age", 18);

        System.out.println(jo.toString());
    }

    @Test
    public void add(){
        JsonObject jo = new JsonObject();
        jo.addProperty("name","katlog");
        jo.addProperty("age", 18);


        List<String> h = new ArrayList<>();
        h.add("football");
        h.add("basketball");
        h.add("game video");
        String s = gson.toJson(h);

        // 用jsonParser构造复杂的JsonElement
        jo.add("hobbies", jsonParser.parse(s));

        Map<String,Object> map = new HashMap<>();
        map.put("hobbiesDetails", h);
        map.put("address", "china beijing");
        jo.add("details", jsonParser.parse(gson.toJson(map)));

        System.out.println("jo = " + pretty.toJson(jo));
    }

}
