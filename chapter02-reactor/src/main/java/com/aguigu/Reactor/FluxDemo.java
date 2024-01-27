package com.aguigu.Reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.time.Duration;


/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/27 21:36
 */
public class FluxDemo {

    public static void main(String[] args) {

    }

    /**
     * Mono<Integer> 只有一个Integer
     * Flux<Integer> 多个Integer
     * @param args
     */

    /**
     * 响应式编程核心: 看懂问档弹珠图
     * 信号:  正常/异常(取消)
     *
     *doOnXXX  API 触发时机
     *1.doONNext :每个数据(流的数据)到达的时候触发
     * 2.doOnEach: 每个元素(流的数据和信号) 到达的时候触发
     * @param args
     * @throws InterruptedException
     */
    public void doOnXXXX(String[] args) throws InterruptedException {
        //SignalType  信号类型
        //关键：doOnNext :表示流中某个元素到达后触发我一个回调
        //doOnxxx要感知某个流的事件，写在流的后面，新流的前面
        Flux.just(1,2,3,4,5,6,7,8,9)
                .doOnNext(integer -> System.out.println("元素到达"+integer))
                .doOnEach(integerSignal -> System.out.println("integerSignal = " + integerSignal))
                .map(integer -> 10/integer)
                .doOnError(throwable -> System.out.println("出错了 " + throwable))
                .doOnNext(integer -> System.out.println("元素哈哈到达" + integer))
                .doOnEach(integerSignal -> System.out.println("新流的元素" + integerSignal));
//        Mono<Integer> just = Mono.just(1);
//        just.subscribe();
        //空流： 链式API中，下面的操作符，操作的是上面的那个流
        //事件感知API : 当流发生什么事的时候，触发一个回调。系统电泳前提定义好的钩子函数(Hook[钩子函数]) ；doOnxxx
        Flux<Integer> integerFlux = Flux.range(1, 7)
                .delayElements(Duration.ofSeconds(1))      //操作老流，变成心流
                .doOnComplete(() -> {
                    System.out.println("流完成了");
                })
                .doOnCancel(() -> {
                    System.out.println("流已经被取消了");
                })
                .doOnError(throwable -> {
                    System.out.println("流出错了");
                })
                .doOnNext(integer -> {
                    System.out.println(integer+"doOnNext");
                });
        integerFlux.subscribe();

        Thread.sleep(2000);   //让主线程睡眠

    }

    /**
     * 测试Flux
     * @param args
     */
    public  void FluxDemo(String[] args) {

        /**
         * Moon ： [0||1]个数据
         * Flux: N个数据
         * 就是所谓的发布者，数据的源头，流的源头
         */

        //多元素的流
        Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);

        //流不消费就没用
        just.subscribe(e-> System.out.println("e = " + e));
        //一个流可以有多个消费
        just.subscribe(e-> System.out.println("e1 = " + e));
        //对于每个消费者都是一样的  广播模式

        System.out.println(" =============== ");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        interval.subscribe(e-> System.out.println("e = " + e));

    }
}
