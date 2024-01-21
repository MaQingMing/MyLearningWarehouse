package com.atguigu.StreamApi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/20 23:17
 */
public class StreamDemo01 {


    public static void main(String[] args) {

        /**
         * Stream
         * 流动性:
         * 1. 流是懒加载的Lazy，不调用的时候不会加载
         */

        /**
         * Stream API
         * 1)把数据封装成流，要到数据流 ；  集合类.stream
         * 2)定义流式操作
         * 3)获取最总结果
         * 流其实相当于一个管道，也相当于一个流水线
         */

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.stream()
                .filter(ele->ele%2==0)
                // intermediate operation. 中间操作
                // terminal operation. 终止操作
                .max(Integer::compareTo)
                .ifPresent(System.out::println);
    }
}
