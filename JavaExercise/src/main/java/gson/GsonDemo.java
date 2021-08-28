package gson;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import lombok.Builder;
import lombok.Data;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

/**
 * @ClassName : GsonDemo
 * @Author : yq
 * @Date: 2021-08-27
 * @Description :
 */
public class GsonDemo {


    public static void main(String[] args) {

        GsonDemo main = new GsonDemo();
        main.test();
    }

    public void test4(){


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class,DateTimeSerializer.class);
        gsonBuilder.create();

        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
    }



    class DateTimeSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {

            return null;
        }
    }

    public void test2(){

        Demo demo = Demo.builder().demoName("this is demo nam").build();
        DemoGeneric<Demo> demoGeneric = DemoGeneric.<Demo>builder().demo("this is demo generic name").data(demo).build();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        System.out.println(gson.toJson(demoGeneric));
    }

    public void test3(){
        String json = "{\"demo\":\"this is demo generic name\",\"data\":{\"demoName\":\"this is demo nam\",\"date\":null}}";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        Type clazz = new TypeToken<DemoGeneric<Demo>>(){}.getType();
        DemoGeneric<Demo> demoDemoGeneric = gson.fromJson(json,clazz);
        System.out.println(demoDemoGeneric);
    }

    public void test1(){
        Demo demo = Demo.builder().date(new Date()).build();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapterFactory(new NullToEmptyAdapterFactory());
        gsonBuilder.registerTypeAdapter(Date.class,new DateNullAdapter());
        gsonBuilder.registerTypeAdapter(String.class,new StringNullAdapter());
        Gson gson=  gsonBuilder.serializeNulls().create();
        System.out.println(gson.toJson(demo));
    }

    /**
     * 字符串 null -> ""
     */
    static class StringNullAdapter extends TypeAdapter<String> {

        @Override
        public void write(JsonWriter out, String value) throws IOException {
            if(value == null){
                out.value("");
                return;
            }
            out.jsonValue(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
            if(in.peek() == JsonToken.NULL){
                in.nextNull();
                return "";
            }
            return in.nextString();
        }
    }

    static class DateNullAdapter extends TypeAdapter<Date> {

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if(value == null){
                out.value("");
            }else {
                //自定义日期处理
                String dateFormatAsString = DateFormat.getDateTimeInstance().format(value);
                out.value(dateFormatAsString);
            }
        }
        @Override
        public Date read(JsonReader in) {
            return null;
        }
    }

    class NullToEmptyAdapterFactory<T> implements TypeAdapterFactory{

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if(rawType == String.class){
                return (TypeAdapter<T>) new StringNullAdapter();
            }
            return null;
        }
    }



    public void test(){
        DemoBasicType demoBasicType = DemoBasicType.builder().build();

        Gson gson = new Gson();
        System.out.println(gson.toJson(demoBasicType));
    }
}

@Builder
@Data
class Demo{

    private String demoName;

    private Date date;
}

@Data
@Builder
class DemoBasicType{

    @SerializedName("妈个蛋")
    private int a;

    private long l;

    private char c;

    private short s;

    private double d;

    private float f;

    private boolean b;

    private byte e;

    private String string;

    private int[] ints;

    private long[] longs;

    private char[] chars;

    private short[] shorts;

    private double[] doubles;

    private float[] floats;

    private boolean[] booleans;

    private byte[] bytes;
}

@Builder
@Data
class DemoGeneric<T>{

    private String demo;

    private T data;

}

