package com.study.stack;

public class Calculator {

    public static void main(String[] args) {
        //中缀表达式
        String expression="7*2*2-5+1-5+3-4"; //18
        //创建两个栈，数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0 ; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        //开始while循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop两个数
                    //在从符号中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //运算结果加进数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else {
                     //如果为空，直接入符号栈
                    operStack.push(ch);
                }
            }else { //如果是数，直接入数栈
                //
                numStack.push(ch - 48);
            }
            //让index+1 ，判断是否扫描到expression最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个结果
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res  = numStack.cal(num1,num2,oper);//计算
            numStack.push(res);//入栈

        }
        //将数栈的最后数，pop出来就是结果
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }



}
//先创建一个栈
//定义一个ArrayStack2表示栈，需要扩展功能
class ArrayStack2{
    //栈的大小
    private int maxSize;
    //数组，数组模拟栈数据就放在数组
    private int[] stack;
    //表示栈顶
    private int top = -1;
    //构造器，初始化栈的大小
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }
    //增加一个方法，可以返回当前，但不是真正的pop
    public int peek(){
        return stack[top];
    }
    //栈满
    public boolean isFull(){
        return top == maxSize -1 ;
    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top -- ;
        return value;
    }
    //遍历栈 ，用于显示
    public void  list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
    //返回运算符的优先级，优先级是程序员来确定的，优先级使用数据来表示
    //数字越大，则优先级就越高
    public int priority(int oper){
        if (oper == '*' || oper =='/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            //假定目前的表达式只有 + ， - ，* ， /
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1 , int num2 , int oper){
        //res 用于存放计算的结果
        int res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }

}