package nowcoder;

/**
 * String与StringBuffer之间的相互转化
 * 
 * @author qiang
 *
 */
public class String_StringBuffer {
	public static void main(String[] args) {
		String string = "Hello world1!";

		// String -> StringBuffer
		// 1. 通过构造方法
		StringBuffer buffer1 = new StringBuffer(string);
		// 2. 通过appends方法
		StringBuffer buffer2 = new StringBuffer();
		buffer1.append(string);

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("hello world2!");

		// StringBuffer -> String
		// 1. 通过tostring方法
		String string1 = stringBuffer.toString();
		// 2.通过构造方法
	}
}
