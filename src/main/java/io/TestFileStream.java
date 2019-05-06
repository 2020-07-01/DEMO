package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileStream {

	public static void main(String[] args) {
		//创建源
		String path = "src/main/java/io/temp.txt";
		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(path);
			//输出
			String string = "qrrqqwrwwe发送发fasfad";
			byte[] b = string.getBytes();//将字符串转换为字节流
			fileOutputStream.write(b, 0, b.length);//写入字符串的所有字节写入到文件中
			fileOutputStream.flush();//刷新输出流，强制写出任何缓冲的输出字节

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			//关闭流
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//输入流,自动关闭流
		try (FileInputStream fileInputStream = new FileInputStream(path)) {

			byte[] b = new byte[1024];
			int len = -1;
			//读取字节数据
			while ((len = fileInputStream.read(b)) != -1) {
				//将字节数组转换为字符串
				String string = new String(b, 0, b.length);
				System.out.println(string);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
