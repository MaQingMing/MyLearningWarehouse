package com.atguigu;

import java.util.function.*;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/19 22:55
 */
public class FunctionDemo {


    public static void main(String[] args) {
        //生产者
        Supplier<String> stringSupplier = ()->"42";
        //断言  是否是一个数字
        Predicate<String> predicate = s -> s.matches("-?\\d+(\\.\\d+)?");
        //转换器
        Function<String,Integer> function = Integer::parseInt;
        //消费者
        Consumer<Integer> consumer = (i)->{
            if (i%2==0){
                System.out.println("是偶数");
            }else {
                System.out.println("非偶数");
            }
        };
        String s = stringSupplier.get();
        boolean test = predicate.test(s);
        if (test){
            Integer apply = function.apply(s);
            consumer.accept(apply);
        }
    }
}
