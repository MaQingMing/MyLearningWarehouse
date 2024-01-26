package com.atguigu.StreamApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/21 11:06
 */
public class StreamDemo03 {


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Person{
        private String name;
        private String sex;
        private Integer age;
    }
    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        list.add(new Person("m qm","男",22));
        list.add(new Person("w xs","男",24));
        list.add(new Person("m hy","男",25));
        list.add(new Person("j h","男",23));

        /**
         * 挑出 年龄大于22的 拿到集合流其实就是拿到集合的深拷贝的值，流的所有操作都是流的元素的引用
         * filter,map,flatmap:  流里面的每一个元素都完整走一个流水线，才能到下一个元素
         * 第一个元素流经所有管道处理后，下一个元素继续执行完整管道流程
         * 声明式:  基于时间的回调
         */

        list.stream()
                .limit(3)                      //限制3条数据
                .filter(person -> person.age>22 )         //由系统自动回调，不由程序员本身处理
                 .peek(System.out::println)             //查看流
                .map(person -> person.name)                            // 一对一映射
                .flatMap(ele->{                                       // 一对多映射，打散或是散列
                    String[] split = ele.split(" ");
                    Stream<String> stream = Arrays.stream(split);
                    return stream;
                })
                .distinct()      //去重
                .sorted(String::compareTo)            //排序
                .forEach(System.out::println);        //循环。终止操作




        /**
         * groupingBy的使用
         */

        /**
         * 数据是自流动的，而不是靠迭代被动流动
         * 推拉模型
         * 推: 流模式 上游有数据，自动推给下游
         * 拉: 迭代器；自己遍历，自己拉取
         */
        Map<String, List<Person>> collect = list.stream()
                .filter(person -> person.age > 23)
                .collect(Collectors.groupingBy(ele -> ele.sex));


        System.out.println("collect = " + collect);




    }
}
