package com.atguigu.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.time.Duration;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/3/3 20:27
 */

@RestController
public class Demo01Controller {

    @RequestMapping(value = "/hahaha")
    public Mono<String> hahaha(){
        return Mono.just("哈哈哈哈");
    }

    @RequestMapping(value = "/hehehe")
    public Flux<String> hehehe(){
        return Flux.just("你好","要加油");
    }


    /**
     * TEXT_EVENT_STREAM_VALUE
     * SSE测试 ， chargpt 都再用  ；服务端推送
     * @return
     */
    @RequestMapping(value = "/sse",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse(){
        return Flux.range(1,10)
                .map(i->"haha"+i)
                .delayElements(Duration.ofMillis(500));
    }



    //SpringMvc 以前怎么用, 基本可以无缝切换
    //底层：需要自己来时编写响应式代码
}
