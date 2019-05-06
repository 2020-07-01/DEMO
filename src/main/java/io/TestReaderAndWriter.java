package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @ClassName:  TestReaderAndWriter   
 * @Description:字符流的测试-Reader和Writer
 * @author: caiji
 * @date: 2019年5月5日 下午6:59:56
 */
public class TestReaderAndWriter {

	public static void main(String[] args) {
		//创建源
		String path = "src/main/java/io/temp3.txt";
		//创建字符缓冲流,并指定缓冲流的大小
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path), 1024)) {
			//写入字符和字符串
			writer.write('h');
			//每个空格均为字符
			writer.write("hello    world");

		} catch (IOException e) {
			e.printStackTrace();
		}

		//读取字符
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int size = 0;
			//每次读取两个字符
			char[] cbuf = new char[2];
			while ((size = reader.read(cbuf, 0, cbuf.length)) != -1) {
				System.out.println(new String(cbuf, 0, size));
			}
			//当没有字符时会读取到-1
			System.out.println("此时字符已经读取完毕：" + reader.read());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
