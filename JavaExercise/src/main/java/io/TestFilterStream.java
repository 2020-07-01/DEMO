package io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

/**
 * @ClassName:  TestFilterStream   
 * @Description:测试过滤器数据流
 * @author: caiji
 * @date: 2019年5月5日 下午4:28:21
 */
public class TestFilterStream {
	
	public static void main(String[] args) {
		String path = "src/main/java/io/temp1.dat";
		try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(path))) {
		//输入名字与分数
		dataOutputStream.writeUTF("Y");
		dataOutputStream.writeDouble(12.4);
		
		dataOutputStream.writeUTF("X");
		dataOutputStream.writeDouble(14.4);
		
		dataOutputStream.writeUTF("V");
		dataOutputStream.writeDouble(16.4);
		
		dataOutputStream.writeUTF("输入字符：");
		dataOutputStream.writeChar('f');
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
