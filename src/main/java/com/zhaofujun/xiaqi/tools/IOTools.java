package com.zhaofujun.xiaqi.tools;

import java.util.Scanner;
import java.util.regex.Pattern;

public class IOTools {
    public static Zuobiao getZuobiao(String name) {
        System.out.println(String.format("系统：等待%s落子:", name));
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        if (Pattern.matches("^\\d+,\\d+$", nextLine)) {
            String[] split = nextLine.split(",");
            return new Zuobiao(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            return getZuobiao(name);
        }
    }
}
