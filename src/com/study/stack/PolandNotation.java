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
    // 方法: 将得到的中缀表达式对应的List=》后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
            //定义两个栈 --> 符号栈
        Stack<String> s1 = new Stack<>();
        //说明: 因为s2这个栈 ， 在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2
        List<String> s2 = new ArrayList<>(); //存储中间结果Lists2

        //遍历ls
        for (String item:ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item) ;
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到右括号为止，并将此一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); // 将（弹出s1栈，消除小括号
            }else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中
                //再次转到（4.1）与s1中新的栈顶运算符做比较
                while (s1.size()!=0&&Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将 中缀表达式转成对应的List
    // s = “1 + （（2+3）x 4）-5”

    public static List<String> toInfixExpressionList(String s){
        // 定义一个List，存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        //这是一个指针，用于遍历 中缀表达式的字符串
        int i = 0;
        // 多位数的拼接
        String str ;
        //遍历到一个字符 ， 就放入到C
        char c ;
        do{
            //如果C是一个非数字，我需要加入到ls
            if ((c = s.charAt(i) )< 48 ||
                    (c = s.charAt(i)) >57
            ){
                ls.add(""+c);
                // i 后移
                i++;
            }
            else {
                //如果是一个多位数，需要考虑多位数
                str = "";
                while (i < s.length()&& (c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
            return ls;
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
//编写一个类Operation可以返回对应的优先级
class Operation{

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //写一个方法 ， 返回对应的优先级数字

    public static int getValue(String operation){

        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-" :
                result = SUB ;
                break;
            case "*" :
                result = MUL ;
                break;
            case "/" :
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }




















}