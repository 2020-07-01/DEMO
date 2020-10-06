package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @ClassName:  TestPrintWriter   
 * @Description:测试文本文件的输入
 * @author: caiji
 * @date: 2019年5月5日 下午6:30:24
 */
public class TestPrintWriter {
	public static void main(String[] args) throws FileNotFoundException {

		String path = "src/main/java/io/temp5.txt";
		File file = new File(path);

		PrintWriter printWriter = new PrintWriter(file);
		printWriter.println("123");
		printWriter.write("faf");
		
		printWriter.println("456");

		printWriter.write("faf");
		printWriter.close();

	}

}
