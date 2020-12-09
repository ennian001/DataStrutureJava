package com.study.recusion;

/**
 *  map[i][j] 为0表示该点没有走过 ， 当为1表示墙 ； 2表示通路可以走 ； 3 表示该店已经走过，但是走不通
 */
public class MiGong {

    public static void main(String[] args) {
        //创建一个二维数组表示迷宫
        int [] [] map = new int[8][7];
        //1表示墙，上下全部设置成1
        for (int i  = 0; i <7 ;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部设置成1
        for (int i = 0 ; i<8 ; i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0; i <  8 ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        setWay(map,1,1);
        System.out.println("<====================================================>");
        for (int i = 0; i < 8  ; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param map 表示地图
     * @param i  从哪个位置开始
     * @param j
     * @return  如果找到通路就返回true 否则返回false
     */
    public static boolean setWay(int [][] map , int i , int j){

        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){
                //如果当前这个点还有没走过
                map[i][j] =2 ;
                //向下走
                if (setWay(map,i+1,j)){
                    return true;
                }
                //向右走
                else if (setWay(map,i,j+1)){
                    return true;
                }
                //向上走
                else if (setWay(map,i-1,j)){
                    return true;
                }
                //向左走
                else if (setWay(map,i,j-1)){
                    return true;
                }
                //表示该点走不通是死路
                else {
                    map[i][j] = 3;
                    return false;
                }
            }else {
                //如果map[i][j] !=0 可能是1 ，2 ，3
                return false;
            }



        }

    }



}
