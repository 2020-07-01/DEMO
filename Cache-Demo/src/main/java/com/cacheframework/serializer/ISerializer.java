package com.cacheframework.serializer;

import java.lang.reflect.Type;

/**
 * @ClassName : ISerializer
 * @Author : yq
 * @Date: 2021-04-28
 * @Description :
 */
public interface ISerializer<T>{

    byte[] serialize(final T obj) throws Exception;

    T deserialize(final byte[] bytes,final Type returnType) throws Exception;
}
