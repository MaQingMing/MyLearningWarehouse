package com.atguigu;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/19 22:23
 */
public class function {

    public static void main(String[] args) {
        /**
         * 函数接口的出入参定义
         * 1.有入参，无出参  function  (消费者)
         */
        BiConsumer<String,Integer> biConsumer = (s,a)->{
            System.out.println(Integer.parseInt(s)+a);
            System.out.println("a = " + a);
        };
        biConsumer.accept("12",3);

        /**
         * 2.有入参，有出参
         * function.apply  (多功能函数)
         */
        Function<String,Integer> stringFunction =(s)->Integer.parseInt(s);
        stringFunction.apply("123");

        /**
         * 无入参，无出参   (普通函数)
         */
        Runnable runnable=()->{
            System.out.println("hahahha");
        };

        new Thread(runnable).start();
        /**
         * 无入参，有出参  (提供者)
         */
        Supplier<String> stringSupplier = ()-> UUID.randomUUID().toString();
        stringSupplier.get();

    }
}
