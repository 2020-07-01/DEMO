package com.cacheframework.common;

/**
 * @ClassName : CacheUtil
 * @Author : yq
 * @Date: 2021-05-04
 * @Description :
 */
public class CacheUtil {

    private static final String SPLIT_STR = "_";

    /**
     * 类名.方法名
     * @param className
     * @param method
     * @param arguments
     * @return
     */
    public static String getDefaultCacheKey(String className, String method, Object[] arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append(getDefaultCacheKeyPrefix(className, method));
        if (null != arguments && arguments.length > 0) {
            sb.append(getUniqueHashStr(arguments));
        }
        return sb.toString();
    }


    public static String getDefaultCacheKeyPrefix(String className, String method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(className);
        if (null != method && method.length() > 0) {
            stringBuilder.append(".").append(method);
        }
        return stringBuilder.toString();
    }


    /**
     * 将Object 对象转换为唯一的Hash字符串
     *
     * @param obj Object
     * @return Hash字符串
     */
    public static String getUniqueHashStr(Object obj) {
        //TODO
        return getMiscHashCode(obj.toString());
    }

    /**
     * 通过混合Hash算法，将长字符串转为短字符串（字符串长度小于等于20时，不做处理）
     *
     * @param str String
     * @return Hash字符串
     */
    public static String getMiscHashCode(String str) {
        if (null == str || str.length() == 0) {
            return "";
        }
        int originSize = 20;
        if (str.length() <= originSize) {
            return str;
        }
        StringBuilder tmp = new StringBuilder();
        tmp.append(str.hashCode()).append(SPLIT_STR).append(getHashCode(str));

        int mid = str.length() / 2;
        String str1 = str.substring(0, mid);
        String str2 = str.substring(mid);
        tmp.append(SPLIT_STR).append(str1.hashCode());
        tmp.append(SPLIT_STR).append(str2.hashCode());

        return tmp.toString();
    }

    /**
     * 生成字符串的HashCode
     *
     * @param buf
     * @return int hashCode
     */
    private static int getHashCode(String buf) {
        int hash = 5381;
        int len = buf.length();

        while (len-- > 0) {
            hash = ((hash << 5) + hash) + buf.charAt(len);
        }
        return hash;
    }
}
