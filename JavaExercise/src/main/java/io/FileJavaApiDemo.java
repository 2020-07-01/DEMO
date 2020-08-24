package io;

import java.io.File;

/**
 * @author yq
 * @date 2020/8/23 18:57
 */
public class FileJavaApiDemo {

    public static void main(String[] args) throws Exception {

        /**
         * 单级目录与多级目录
         */
        String path = "D:" + File.separator + "工作文档";
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
        /**
         * 判断目录与文件
         */
        System.out.println("isFile:" + file.isFile());
        System.out.println("isDirectory:" + file.isDirectory());

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                System.out.println(files[i].getName());
            }
        }

        /**
         * 在创建文件之前需判断文件的父目录是否存在
         */
        File file1 = new File("D:" + File.separator + "data", "ttt");
        if (!file1.exists()) {
            file1.createNewFile();
        }

    }
}
