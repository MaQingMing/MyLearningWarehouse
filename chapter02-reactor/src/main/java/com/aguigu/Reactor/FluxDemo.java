package com.aguigu.Reactor;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
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
        Flux<Integer> range = Flux.range(1, 7);
//        range.subscribe();          //流被订阅；默认订阅
//        range.subscribe(v-> System.out.println("v = " + v));             //指定订阅规则，正常消费者，只消费正常元素
//
//        range.subscribe(v-> System.out.println("v = " + v),    //流元素消费
//                throwable -> System.out.println("throwable = " + throwable),    //感知异常结束
//                ()-> System.out.println("流结束了"));    //感知正常结束



        range.subscribe(new BaseSubscriber<Integer>() {

            //订阅关系绑定的时候触发
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("绑定了 = " + subscription);
                requestUnbounded();       //要无限个数据
                request(1);            //要一个数据
            }

            //数据到达时处理
            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("数据到达= " + value);
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                super.hookOnComplete();
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }

            @Override
            protected void hookFinally(SignalType type) {
                super.hookFinally(type);
            }
        });

    }

    /**
     * 限流
     */
    public void limit(){

        Flux.range(1,1000)
                .log()
                //限流触发,看上游是怎么获取数据的
                .limitRate(100) //一次性抓取100个元素，第一次 request(100) ，以后request(75)
                .subscribe();
        //75% 抓取策略 ： limitRate(100)
        //第一次抓取100个数据，如果75%的元素已经处理了。继续抓取新的75%元素
    }

    public void dispose(){
        Flux<Integer> flux = Flux.range(1, 1000)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> 7)
                .log();


        //消费者是实现了Disposable 可取消
        Disposable subscribe = flux.subscribe(System.out::println);
    }

    /**
     * 自定义处理规则
     */
    public void handle(){
        Flux.range(1,10)
                .handle((value,sink)->{
                    System.out.println("value = " + value);
                    sink.next(value);
                })
        .log().subscribe();

    }

    /**
     * 编程方式创建序列
     * sink : 接收器 水槽 通道
     * Source ;数据源  sink: 接收端
     */
    public void generate(){
        Flux<Object> generate = Flux.generate(() -> 0,       //初始state的值
                (state, sink) -> {
                    if (state <= 10) {
                        sink.next(state);     //把元素传出去
                    } else {
                        sink.complete();    //完成 ，完成信号
                    }
                    if (state == 7) {
                        sink.error(new RuntimeException("我不喜欢7"));
                    }
                    return state + 1; //返回新的迭代state值
                }
        );

    }

    /**
     * 缓冲区
     */
    public void buffer(){
        Flux.range(1,100)
                .buffer(30)           //一个缓存区里面有30个【1-30】 【31-60】
                .subscribe();
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
