package covertUtils;

/**
 * @author yq
 * @date 2020/7/5 10:21
 * @Description: 8种基本数据类型及String之间的转换
 */
public class CovertBaseDataType {


    /**
     * 整型转换为字符串
     *
     * @param intData
     * @return
     */
    public static String covertIntToString(int intData) {

        String string1 = String.valueOf(intData);
        String string = Integer.toString(intData);
        return string;
    }


    /**
     * 整型字符串转换为整型
     *
     * @param stringData
     * @return
     */
    public static int covertStringToInt(String stringData) {
        int result = Integer.parseInt(stringData);
        Integer result1 = Integer.valueOf(stringData);
        return result;
    }


    /**
     * 整型转换为字符型，仅限0到9
     *
     * @param intData
     * @return
     */
    public static char covertIntToChar(int intData) {
        if (intData > 9 || intData < 0) {
            throw new NumberFormatException("illegal inData:" + intData);
        }
        String string = String.valueOf(intData);
        char result = string.charAt(0);
        return result;
    }


    /**
     * 字符型转换为整型
     *
     * @return
     */
    public static int covertCharToInt(char charData) {
        String string = String.valueOf(charData);
        int result = Integer.valueOf(string);
        return result;
    }


    /**
     * 字符数组转换为整型
     *
     * @return
     */
    public static int covertCharArrayToInt(char[] charsData) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < charsData.length; i++) {
            stringBuilder = stringBuilder.append(String.valueOf(charsData[i]));
        }
        int result = Integer.parseInt(stringBuilder.toString());
        return result;
    }

}
