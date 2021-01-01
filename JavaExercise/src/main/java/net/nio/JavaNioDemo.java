package net.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @ClassName : ChannelDemo
 * @Author : yq
 * @Date: 2020-12-01
 * @Description :
 */
public class JavaNioDemo {

    public static void main(String[] args) {

        testChannel();
        //testFileChannel();
    }


    public static void testFileChannel() {
        try {
            String string = "hello world!";
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\workspace\\DEMO\\JavaExercise\\src\\main\\java\\net.nio\\file\\helloWorld.txt");
            FileChannel fileChannel = fileOutputStream.getChannel();

            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //将数据写入到buffer
            byteBuffer.put(string.getBytes());
            //状态反转
            byteBuffer.flip();
            //将缓冲区数据写入到channel
            fileChannel.write(byteBuffer);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void testChannel() {

        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\workspace\\DEMO\\JavaExercise\\src\\main\\java\\net.nio\\file\\helloWorld.txt");

            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());

            channel.read(byteBuffer);

            System.out.println(Charset.forName("utf-8").decode(byteBuffer).toString());

            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test(){
        try {

            RandomAccessFile randomAccessFile = new RandomAccessFile("","");

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            //获取一个只读buffer
            ByteBuffer byteBufferRead = byteBuffer.asReadOnlyBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
