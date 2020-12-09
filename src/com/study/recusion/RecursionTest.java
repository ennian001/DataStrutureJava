package com.study.recusion;

public class RecursionTest {


    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(3));
    }
    //打印问题
    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }
    //阶乘问题
    public static int factorial(int  n){
        if (n == 1){
            return 1;
        }else {
            //如果传入的是2 ==>factorial(2-1)*2
            //如果传入的是3 ==>factorial(3-1)*3
            return factorial(n-1)*n;//3*2
        }
    }

}
