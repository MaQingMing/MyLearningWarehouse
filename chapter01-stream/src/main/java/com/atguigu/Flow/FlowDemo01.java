package com.atguigu.Flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/26 22:52
 */

/**
 * 响应式编程:
 * 1.底层: 基于数据缓冲队列+消息驱动模型+异步回调机制
 * 2.编码： 流式编程+链式调用+声明式API
 * 3.效果： 优雅全异步+消息实时处理+高吞土量+占用少量资源
 */
public class FlowDemo01 {

   static class MyProcesser extends SubmissionPublisher<String> implements Flow.Processor<String,String>{

       private Flow.Subscription subscription;          //用来保存关系
       @Override   //在订阅 onXXX发生时 ：在XXX事件发生时，执行这个回调
       public void onSubscribe(Flow.Subscription subscription) {
           System.out.println("绑定完成");
           this.subscription=subscription;
           subscription.request(1);   //从上游请求一个数据
       }
       @Override   //在下一个元素到达时 ；接收到新数据
       public void onNext(String item) {
           item+=" : 哈哈 ";
           submit(item);
           subscription.request(1);
       }
       @Override  //在发生错误时
       public void onError(Throwable throwable) {
           System.out.println("接收到错误信号 " + throwable);
       }

       @Override  //在完成时
       public void onComplete() {
           System.out.println("接收到完成信号");
       }
   }

    /**
     * 1.Publisher: 发布者
     * 2.Subscriber: 订阅者
     * 3.Subscription: 订阅关系
     * 4.Processer : 处理器
     */

    /**
     * 观察者模式
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {


        //定义一个订阅者；订阅者感兴趣发布者的数据
        Flow.Subscriber<String> subscriber =new Flow.Subscriber<String>() {

            private Flow.Subscription subscription;
            @Override   //在订阅 onXXX发生时 ：在XXX事件发生时，执行这个回调
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(Thread.currentThread()+"订阅开始了 " + subscription);
                this.subscription=subscription;
                subscription.request(1);   //从上游请求一个数据
            }
            @Override   //在下一个元素到达时 ；接收到新数据
            public void onNext(String item) {
                System.out.println(Thread.currentThread()+"订阅者接收数据 = " + item);
                subscription.request(1);
            }

            @Override  //在发生错误时
            public void onError(Throwable throwable) {
                System.out.println("接收到错误信号 " + throwable);
            }

            @Override  //在完成时
            public void onComplete() {
                System.out.println("接收到完成信号");
            }
        };
        //定义一个发布者 ： 发布数据
        SubmissionPublisher<String> publisher =new SubmissionPublisher<>();
        MyProcesser myProcesser = new MyProcesser();
        //绑定发布者和订阅者
        publisher.subscribe(myProcesser);  //此时处理器相当于订阅者
        myProcesser.subscribe(subscriber); //此时处理器相当于发布者
        //链表关系绑定出责任链
        for (int i = 0; i < 10; i++) {
            publisher.submit("p-"+i);
            //发布的数据存在缓冲区 buffer
        }
        //发布通道关闭
        publisher.close();

        Thread.sleep(20000);
    }
}
