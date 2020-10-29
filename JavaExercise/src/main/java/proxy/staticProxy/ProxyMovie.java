package proxy.staticProxy;

/**
 * @ClassName : ProxyMovie
 * @Author : yq
 * @Date: 2020-10-29
 * @Description : 代理类(增强类)
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
     * Aop就是代理模式 在方法执行前后进行加强
     * BeanPostProcessor也是
     */
    private Movie movie;

    ProxyMovie(Movie movie) {
        this.movie = movie;
    }


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
