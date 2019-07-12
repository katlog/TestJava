
/**  
 * @Title: TestGsonEnum.java
 * @Package: org.person.dfw.gson
 * @author: katlog
 * @date: 2017年5月11日 上午9:58:03
 * @version: V1.0  
 */ 
package dfw.json.gson;

import com.google.gson.*;
import org.junit.Test;

import java.lang.reflect.Type;

public class TestJson_GsonEnum {
    enum PackageState {  
        PLAY, UPDATE, UPDATING, DOWNLOAD, DOWNLOADING,  
    }  
    public class PackageItem {  
        private String name;  
        private PackageState state;  
        private String size;  
        public String getName() {  return name;  }  public void setName(String name) {  this.name = name;}  
        public PackageState getState() {  return state;  }   public void setState(PackageState state) {  this.state = state;  }  
        public String getSize() { return size;  }  public void setSize(String size) {   this.size = size;}  
        @Override  public String toString() {   return "PackageItem [name=" + name + ", size=" + size + ", state="  + "]"; }  
    }  
    
    public class EnumSerializer implements JsonSerializer<PackageState>,  
        JsonDeserializer<PackageState> {  
        
        // 对象转为Json时调用,实现JsonSerializer<PackageState>接口  
        @Override  
        public JsonElement serialize(PackageState state, Type arg1,  
                JsonSerializationContext arg2) {  
            return new JsonPrimitive(state.ordinal());  
        }  
        
        // json转为对象时调用,实现JsonDeserializer<PackageState>接口  
        @Override  
        public PackageState deserialize(JsonElement json, Type typeOfT,  
                JsonDeserializationContext context) throws JsonParseException {  
            if (json.getAsInt() < PackageState.values().length)  
                return PackageState.values()[json.getAsInt()];  
            return null;  
        }
    }  
    
    @Test public void test(){
        GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(PackageState.class, new EnumSerializer());  
        Gson gson = gsonBuilder.create();  
        PackageItem item = new PackageItem();  
        item.setName("item_name");  
        item.setSize("500M");  
        item.setState(PackageState.UPDATING);// 这个 state是枚举值  
  
        String s = gson.toJson(item);  
        System.out.println(s);  
  
        System.out.println("--------------------------------");  
  
        PackageItem retItem = gson.fromJson(s, PackageItem.class);  
        System.out.println(retItem);  
    }  
}
