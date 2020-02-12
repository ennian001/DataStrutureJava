package com.study.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器
 */
public class PolandNotation {

    public static void main(String[] args) {
        //先定义给逆波兰表达式
        //（30 + 4）*5 -6 = 》 30 4 + 5 * 6 -


    }
    //将一个逆波兰表达式 ， 依次将数据和运算符 放到ArrayList中
    public static List<String> getListString(String suffixExpression){

        String[] split = suffixExpression.split(" ");
        List<String> list  = new ArrayList<>();
        for (String ele:split) {
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        //创建栈 ， 只需要一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //正则表达式来取数
            if (item.matches("\\d+")){
                //如果匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop 出两个数 ， 并运算 ， 再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0 ;
                if (item.equals("+")){
                    res = num1+num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1*num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把res 入栈
                stack.push(""+res);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
