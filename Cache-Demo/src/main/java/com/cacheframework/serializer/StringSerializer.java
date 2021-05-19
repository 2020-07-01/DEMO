package com.cacheframework.serializer;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName : StringSerializer
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
public class StringSerializer implements ISerializer<String> {

    private final Charset charset;

    public StringSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public StringSerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(String obj) {
        return new byte[0];
    }

    @Override
    public String deserialize(byte[] bytes, Type returnType) {
        return (bytes == null ? null : new String(bytes, charset));
    }
}
