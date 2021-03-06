package com.study.stack;


public class ArrayStackDemo {
}


//定义一个栈
class ArrayStack{
    //栈的大小
    private int maxSize;
    //数组。，数组模拟栈，数据就放在该数组
    private int[] stack ;
    //top 表示栈顶，初始化为-1
    private int top = -1 ;

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1 ;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //先判断栈是否满
        if (isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈 pop ，将栈顶数据返回
    public int pop(){
        //先判断栈是否空
        if (isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空 ， 没有数据");
        }
        int value = stack[top];
        top -- ;
        return value;
    }
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}