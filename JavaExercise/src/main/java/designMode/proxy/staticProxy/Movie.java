package designMode.proxy.staticProxy;

/**
 * @ClassName : Movie
 * @Author : yq
 * @Date: 2020-10-29
 * @Description : 电影
 */
public interface Movie {

    /**
     * 代理类和被代理类都要实现同一个接口
     * 代理类对play()方法进行增强
     */
    void play();
}
