package designMode.proxy.staticProxy;

/**
 * @ClassName : ProxyMovie
 * @Author : yq
 * @Date: 2020-10-29
 * @Description : 代理类(增强类)
 *
 * 在动态代理中不需要再创建此代理类  jdk通过反射动态生成代理对象
 */
public class ProxyMovie implements Movie {
    /**
     * 代理类和被代理类都要实现同一个接口
     * 代理类对play()方法进行增强
     *
     * 静态代理中：
     * 被代理作为属性注入
     *
     * 动态代理：
     * 反射生成被代理类及其被代理类的方法
     * 代理类中通过Method的invoke方法执行被代理类的方法
     *
     *
     * Aop就是代理模式 在方法执行前后进行加强
     * BeanPostProcessor也是
     */
    private Movie movie;

    ProxyMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * 目标类和代理类都实现了相同的接口的相同方法
     * 当接口方法变化时，代理类和目标类的都要重写代码
     * 每个目标类都要有自己的代理类
     * 而动态代理就是程序在运行过程中动态的生成代理类，并执行其中的方法
     */
    @Override
    public void play() {
        guangGao(true);
        movie.play();
        guangGao(false);
    }


    private void guangGao(Boolean flag) {
        if (flag) {
            System.out.println("电影马上开始了，播放广告。。。。。。");
        } else {
            System.out.println("电影结束了，播放广告。。。。。。");
        }

    }
}
