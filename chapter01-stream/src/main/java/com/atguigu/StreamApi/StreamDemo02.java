package com.atguigu.StreamApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/21 0:03
 */


public class StreamDemo02 {

    public static void main(String[] args) {
        // 1.数据流  2.N个中间操作   3.一个终止操作

        //创建流
        Stream<Integer> stream = Stream.of(1,2,3);
        Stream<Integer> concat = Stream.concat(Stream.of(2, 3, 4), stream);
        Stream.Builder<Object> add = Stream.builder().add(3).add(5);

        //2)从容器集合中获取这个流
        List<Integer> list = new ArrayList<>();
        Stream<Integer> stream1 = list.stream();


        // 流是并发还是不并发？和for有啥区别？ 流也是for循环挨个处理；默认不并发，也可以并发
        //并发以后，自行处理多线程安全问题
        List aaa =new ArrayList();
        //有状态数据将产生安全问题，千万不要写
        // 流的所有操作都是无状态的，数据状态仅在函数里面有效，不要溢出函数外
        //处理并发问题就是枷锁 synchronized  这样就是串行操作了
        long count = Stream.of(1, 2, 3, 4, 5)
                .parallel()                        // 并发流，中间操作
                .filter(i -> {
                    System.out.println("Thread.currentThread() = " + Thread.currentThread());
                    System.out.println("i = " + i);
                    return i > 2;
                })
                .count();

    }
}
