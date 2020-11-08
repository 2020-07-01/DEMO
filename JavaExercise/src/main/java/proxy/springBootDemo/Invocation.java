package proxy.springBootDemo;

import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName : Invocation
 * @Author : yq
 * @Date: 2020-11-01
 * @Description : 执行类
 */
public class Invocation {

    //参数
    private Object params[];

    //目标类
    private Object target;

    //方法
    private Method method;


    public Invocation(Object target,Method method,Object[] params){
        this.target = target;
        this.method = method;
        this.params = params;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,params);
    }

}
