package com.atguigu.Lambda;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/1/17 23:03
 */

/**
 * 函数式接口 ： 只要是函数式接口就可以用Lambda表达式
 *              接口中有一个未实现的方法
 */


@FunctionalInterface             //检查注解，帮我们快速检查我们写的接口是否是函数式接口
interface math{
    int sum(int i , int j);
    default  int sum01(int i , int j ){
        return i+j;
    }          //默认实现
}


class mathImpl implements math{

    @Override
    public int sum(int i, int j) {
        return i*j;
    }
}



public class lambda {

    public static void main(String[] args) {

        //1.自己创建对象实现
        math m =new mathImpl();
        System.out.println("m.sum(2,3) = " + m.sum(2, 3));


        //2.创建匿名实现类
        math math =new math() {
            @Override
            public int sum(int i, int j) {
                return i*i+j*j;
            }
        };
        System.out.println("math.sum(5,3) = " + math.sum(5, 3));


        //3.Lambda 表达式写法 : 语法糖  参数列表 + 箭头 + 方法体


        math math1 = (i,j)->{
            return i*j;
        };
        System.out.println("math1.sum(5,6) = " + math1.sum(5, 6));


        //完整写法如上
        /**
         * 简化写法
         * 参数类型可以不写，但是不可以修改，只写参数名，参数名可以任意
         * 参数可以只有一个，也可以是一个()，也可以是一个参数
         * 例如
         *      () ->{};
         *      x->{return x };
         *
         * 如果方法体中只有一句话。可以省略{}
         * 列如:
         *    (x,y)-> return x+y;
         *
         */

        /**
         * 总结:
         *  1。 Lambda 的使用  ： (参数)-》{方法体}
         *  2。  @FunctiionInterface     注解帮我们检查是否是函数式接口  如果是的话可以用Lambda简化
         */


    }
}
