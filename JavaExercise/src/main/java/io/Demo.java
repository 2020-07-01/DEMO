package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author yq
 * @date 2020/8/23 19:55
 */
public class Demo {
    public static void main(String[] args) throws IOException {

        String content = "现在通过一系列的分析已经可以清楚字节流与字符流的基本操作了，但是对于这两类流依然是存在有区别的，重点来分析一下输出的处理操作。在使用OutputStream和Writer输出的最后都使用了close()方法进行了关闭处理在使用OutputStream类输出的时候，如果没有使用close()方法关闭输出流会发现内容依然可以实现正常的输出；但是如果在使用Writer的时候没有使用close()方法关闭输出流，那么这个时候内容将无法进行输出，因为Writer使用到了缓冲区，当使用close()方法的时候实际上会出现有强制刷新缓冲区的情况，所以这个时候会将内容进行输出，如果没有关闭，那么将无法进行输出操作，所以此时如果在不关闭的情况下想将全部的内容进行输出可以使用flush()方法强制性清空。";
        String path = "D:"+ File.separator + "content.txt";
        File file = new File(path);
       /* OutputStream outputStream = new FileOutputStream(new File(path));
        outputStream.write(content.getBytes());*/

        Writer writer = new FileWriter(path);
        writer.write(content);
        writer.flush();

        System.getProperties().list(System.out);
    }
}
