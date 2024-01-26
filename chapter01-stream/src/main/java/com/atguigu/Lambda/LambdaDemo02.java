package com.atguigu.Lambda;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/19 10:47
 */


public class LambdaDemo02 {

    public static void main(String[] args) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("okok");
                    }
                }
        ).start();


        new Thread(()->{
            System.out.println("hahaha");
        }).start();

        //最佳实战 :
        //1.以后调用某个方法传入参数时,这个参数实例是一个接口对象，且只定以了一个方法，就直接用lambda表达式
    }
}
