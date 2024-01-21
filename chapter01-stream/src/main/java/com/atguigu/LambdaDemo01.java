package com.atguigu;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/17 23:24
 */
public class LambdaDemo01 {


    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Abab");
        strings.add("Csdasd");
        strings.add("Bdadas");

        Collections.sort(strings,(o1,o2)->{
            return o1.compareTo(o2);
        });

        //简化写法
        //  类  :  实例方法
        Collections.sort(strings,String::compareTo);
        System.out.println("strings = " + strings);
    }
}
