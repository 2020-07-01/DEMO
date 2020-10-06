package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName:  TestBuffer   
 * @Description:测试缓冲流
 * @author: caiji
 * @date: 2019年5月5日 下午6:30:02
 */
public class TestBuffer {

	public static void main(String[] args) {
		//创建源
		String path = "src/main/java/io/temp1.txt";

		//使用缓冲输入流
		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path), 1024)) {

			byte[] b = new byte[1024];
			int len = -1;
			//读取字节数据
			while ((len = bufferedInputStream.read(b)) != -1) {
				//将字节数组转换为字符串
				String string = new String(b, 0, b.length);
				System.out.println(string);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
