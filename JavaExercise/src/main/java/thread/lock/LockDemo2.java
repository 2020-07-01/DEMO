package thread.lock;

/**
 * @ClassName : LockDemo2
 * @Author : yq
 * @Date: 2020-09-17
 * @Description : 锁消除
 */
public class LockDemo2 {


    /**
     * String 相加 使用StringBuffer.append()
     * <p>
     * 此方法之内的锁永远不会逃逸到此方法之外
     * 这里的锁在编译期间会被消除掉
     *
     * @param string1
     * @param string2
     * @param string3
     * @return
     */
    public String concatString(String string1, String string2, String string3) {
        return string1 + string2 + string3;
    }

    Object object = new Object();

    public void test() {
        synchronized (object) {
            System.out.println("");
        }
    }

    /**
     * 每次调用方法的时候都会创建一个新的对象
     * 此对象不会出现在当前调用的线程之外
     */
    public void test1() {
        Object o = new Object();
        synchronized (o) {
            System.out.println();
        }
    }

    public static void main(String[] args) {

        LockDemo2 lockDemo2 = new LockDemo2();
        String string = lockDemo2.concatString("hello", "world", "!");

    }
}
