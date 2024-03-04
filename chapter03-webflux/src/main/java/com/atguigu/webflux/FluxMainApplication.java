package com.atguigu.webflux;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;
import java.net.URI;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/3 19:51
 */
public class FluxMainApplication {


    public static void main(String[] args) throws IOException {

        //快速编写一个能处理的服务器

        //1.创建一个能处理Http请求的处理器，参数:请求、响应； 返回值: Mono<Void> 代表处理完成的信号
        HttpHandler httpHandler = (serverHttpRequest,serverHttpResponse)->{
            URI uri = serverHttpRequest.getURI();
            serverHttpRequest.getCookies();
            serverHttpRequest.getHeaders();
            System.out.println("请求进来.... " + uri);

            serverHttpResponse.getStatusCode() ;     //  获取状态码\
            //服务器响应给前端的，放在缓冲区里面
            DataBufferFactory factory = serverHttpResponse.bufferFactory();//   buffer工厂
            DataBuffer databuffer = factory.wrap(new String("服务器响应的" +uri.toString()).getBytes());
//            serverHttpResponse.setComplete();        //响应结束
            return serverHttpResponse.writeWith(Mono.just(databuffer));

        };


        //2. 启动一个服务器 ，监听8080端口，接收数据，拿到数据交给Httphandler进行处理请求
        ReactorHttpHandlerAdapter adapter =new ReactorHttpHandlerAdapter(httpHandler);

        //3.启动Netty的服务器
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter)
                .bindNow();   // 现在就绑定


        System.out.println("服务器启动");
        System.in.read();
        System.out.println("服务器结束");
    }
}
