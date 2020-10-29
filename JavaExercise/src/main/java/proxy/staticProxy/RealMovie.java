package proxy.staticProxy;

/**
 * @ClassName : RealMovie
 * @Author : yq
 * @Date: 2020-10-29
 * @Description : 被代理类(真正类)
 */
public class RealMovie implements Movie {

    /**
     * 代理类和被代理类都要实现同一个接口
     * 代理类对play()方法进行增强
     */
    @Override
    public void play() {
        System.out.println("播放电影。。。。。。");
    }
}
