package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestObjectStream {

	public static void main(String[] args) {
		//创建源
		String path = "src/main/java/io/temp2.txt";
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {

			//输出字符串
			objectOutputStream.writeUTF("hello world");
			//输出对象
			objectOutputStream.writeObject("hello world1");
			//创建对象在输出
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("helloworld3");
			objectOutputStream.writeObject(arrayList);

		} catch (IOException e) {

			e.printStackTrace();
		}

		//输入流,自动关闭流
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
			//去读字符串
			System.out.println(objectInputStream.readUTF());
			System.out.println(objectInputStream.readObject());
			ArrayList<String> list = (ArrayList<String>) objectInputStream.readObject();
			System.out.println(list.get(0));
			
		} catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

}
