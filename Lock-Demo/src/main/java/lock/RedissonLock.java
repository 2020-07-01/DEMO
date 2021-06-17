package lock;


/**
 * @ClassName : RedissonLock
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
public class RedissonLock extends AbstractRedisLock {

    @Override
    public boolean doTryLock(String key, String value, int expire) {
        return false;
    }



    @Override
    public boolean doReleaseLock(String key, String value) {
        return false;
    }
}
