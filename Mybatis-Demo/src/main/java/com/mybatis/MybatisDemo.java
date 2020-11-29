package com.mybatis;


import com.mybatis.mapper.UserMapper;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : MybatisDemo
 * @Author : yq
 * @Date: 2020-11-29
 * @Description :
 */
public class MybatisDemo {

    public static void main(String[] args) {
        /**
         * mybatis原生写法
         */
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(MybatisDemo.class.getClassLoader(), new Class<?>[]{UserMapper.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Select annotation = method.getAnnotation(Select.class);
                String[] value = annotation.value();
                //获取参数对应关系
                Map<String,Object> stringObjectMap = buildMethodArgNameMap(method,args);
                //拼接sql

                //创建jdbc连接并执行

                //返回结果

                return null;
            }
        });


        userMapper.selectAll(1,"test");


    }


    /**
     * 参数 Name value
     * @param method
     * @param args
     * @return
     */
    public static Map<String,Object> buildMethodArgNameMap(Method method,Object[] args){
        Map<String,Object> map = new HashMap<>();
        //此处需要设置编译参数 否则返回arg0,arg1
        Parameter[] parameterArray = method.getParameters();
        final Integer[] index = {0};
        Arrays.asList(parameterArray).forEach(parameter -> {
            String name = parameter.getName();
            map.put(name,args[index[0]]);
            index[0]++;
        });

        return map;
    }


}
