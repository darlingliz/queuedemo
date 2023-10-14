import java.io.*;
import java.util.LinkedHashMap;
import java.util.Set;
// io用的不熟。
public class Arrayimplm {
    public static void main(String[] args) throws IOException {
        //二维数组的定义
        int[][] board = new int[5][5];
        //0 空， 1 黑棋   2 白棋
        board[2][4] = 1;
        board[3][1] = 1;
        board[4][2] = 2;
        board[3][2] = 2;
//便利输出二维数组
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println("");
        }

/*
二维数组向稀疏数组转变
查找二维数组中有几个不一样的数，位置，值是什么。

 */
        //得到二维数组中不是0的个数
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    count++;
                }
            }
        }
        //count的个数可以确定稀疏数组的长度
        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = board.length;
        sparseArray[0][1] = board[0].length;
        sparseArray[0][2] = count;
        int k = 1;
        //便利二维数组给稀疏数组赋值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                int num1 = 0;
                int num2 = 1;
                int num3 = 2;
                sparseArray[k][num1] = i;
                sparseArray[k][num2] = j;
                sparseArray[k][num3] = board[i][j];
                k++;
            }
        }
        ObjectOutputStream stream = null;
        String path = "d:\\chess.txt";
        //遍历稀疏数组通过对象流存入文件中，达到存档的目的。
        //打开文件是乱码的形式，不需要换行。

        try {
          stream = new ObjectOutputStream(new FileOutputStream(path));
            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray[i].length; j++) {
                    System.out.print(sparseArray[i][j] + " ");
                    stream.writeInt(sparseArray[i][j]);
                }
                System.out.println(" ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(stream != null) {
                stream.close();
            }
        }

        //把稀疏数组从文件中拿到内存中
        System.out.println("-----------");
        ObjectInputStream stream1 = null;
try {
  stream1 = new ObjectInputStream(new FileInputStream(path));
    int num = 1;
    int row = 0;
    int data = 0;
    while ((data = stream1.readInt()) != -1) {
        if (num == 1) {
            sparseArray[row][0] = data;
            num++;
            continue;
        }
        if (num == 2) {
            sparseArray[row][1] = data;
            num++;
            continue;
        }

        if (num == 3) {
            sparseArray[row][2] = data;
            row++;
            num = 1;
        }
    }
}catch (Exception e){
    e.getMessage();
}finally {
    if(stream1 != null){
        stream1.close();
    }
}
        for(int i = 0; i < sparseArray.length; i++){
            for(int j = 0; j < sparseArray[i].length;j++){
                System.out.print(sparseArray[i][j] + " ");
            }
            System.out.println(" ");
        }
        //把稀疏数组转变为二维数组
        int rows = sparseArray[0][0];
        int columns = sparseArray[0][1];
        int[][] returnArray = new int[rows][columns];
        for(int i = 1; i < sparseArray.length; i++){
            returnArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for(int i = 0 ; i < returnArray.length; i++){
            for(int j = 0 ; j < returnArray[i].length; j++){
                System.out.print(returnArray[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

}


