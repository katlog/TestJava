
/**  
 * @Title: Test3waysReadJson.java
 * @Package: org.person.dfw.jackjson
 * @author: katlog
 * @date: 2017年4月13日 下午2:17:03
 * @version: V1.0  
 */ 
package dfw.json.jackjson.read;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

class User {  
    public enum Gender { MALE, FEMALE };  
    public static class Name {  
      private String _first, _last;  
      public String getFirst() { return _first; }  
      public String getLast() { return _last; }  
      public void setFirst(String s) { _first = s; }  
      public void setLast(String s) { _last = s; }  
    }  
  
   private Gender _gender;  
   private Name _name;  
   private boolean _isVerified;  
   private byte[] _userImage;  
  
   public Name getName() { return _name; }  
   public boolean isVerified() { return _isVerified; }  
   public Gender getGender() { return _gender; }  
   public byte[] getUserImage() { return _userImage; }  
  
   public void setName(Name n) { _name = n; }  
   public void setVerified(boolean b) { _isVerified = b; }  
   public void setGender(Gender g) { _gender = g; }  
   public void setUserImage(byte[] b) { _userImage = b; }  
}

/**
 * jackjson 3种方式
 *       数据绑定模式：使用最方便
                流模式：性能最佳
                树模式：最灵活
 * @author: katlog
 * @date: 2017年4月13日 下午2:21:41
 */ 
public class TestJson_JackJson3wayReadJson {

    /**
     * 数据绑定模式为例
     */ 
    @Test
    public void dataBind() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally  
        //json转对象
        User user = mapper.readValue(new File("json/org/person/dfw/jackjson/read/user.json"), User.class); 
        System.out.println(user.getGender()+user.getName().getFirst());
        //对象转json
        mapper.writeValue(new File("json/org/person/dfw/jackjson/read/user-dataBind.json"), user);  
    }
    
    /**
     * 树模式例子
     */ 
    @Test
    public void tree() throws JsonProcessingException, IOException{
        ObjectMapper m = new ObjectMapper();  
        // can either use mapper.readTree(processor), or mapper.readValue(processor, JsonNode.class);
        JsonNode rootNode = m.readTree(new File("json/org/person/dfw/jackjson/read/user.json"));  
        // ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"  
        JsonNode nameNode = rootNode.path("name");  
        String lastName = nameNode.path("last").textValue();
        if ("xmler".equalsIgnoreCase(lastName)) {  
          ((ObjectNode)nameNode).put("last", "Jsoner");  
        }  
       // and write it out:  
       m.writeValue(new File("json/org/person/dfw/jackjson/read/user-tree.json"), rootNode);  
    }
    
    /**
     * 流模式   这是最高效的模式。
     */ 
    @Test
    public void io(){
        /**生成json*/
//        JsonFactory f = new JsonFactory();  
//        JsonGenerator g = f.createJsonGenerator(new File("user.json"));  
//          
//        g.writeStartObject();  
//        g.writeObjectFieldStart("name");  
//        g.writeStringField("first", "Joe");  
//        g.writeStringField("last", "Sixpack");  
//        g.writeEndObject(); // for field 'name'  
//        g.writeStringField("gender", Gender.MALE);  
//        g.writeBooleanField("verified", false);  
//        g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)  
//        byte[] binaryData = {};  
//        g.writeBinary(binaryData);  
//        g.writeEndObject();  
//        g.close(); // important: will force flushing of output, close underlying output stream  

        /**解析json*/
    }
    
}
