package proxy.staticProxy;

/**
 * @ClassName : DemoMain
 * @Author : yq
 * @Date: 2020-10-29
 * @Description :
 */
public class DemoMain {

    public static void main(String[] args) {

        Movie movie = new RealMovie();

        ProxyMovie proxyMovie = new ProxyMovie(movie);

        proxyMovie.play();
    }
}
