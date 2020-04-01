package io;

import java.io.Console;

/**
 * @author :qiang
 * @date :2019/10/19 下午7:26
 * @description :
 * @other :
 */
public class TestConsole {

    public static void main(String[] args) {
        Console console = System.console();
        String username = console.readLine("User Name:");
        char[] password = console.readPassword("Password:");

    }
}
