import java.util.Scanner;

public class ArrayQueueDome {
    public static void main(String[] args) {
        int mixSize = 3;
        Queque queque = new Queque(mixSize);
        Scanner scanner = new Scanner(System.in);
        boolean bool = true;

        while(bool){
            System.out.println("------------------");
            System.out.println("0---判queue是否满 ");
            System.out.println("1---判queue是否空  ");
            //我想让在进入选项的时候有反悔的功能，但要是要添加的数就是-1怎么办？
            System.out.println("2---元  素  入 队  (-1退出选择)");
            System.out.println("3---元  素  出 队  (-1退出选择)");
            System.out.println("4---遍  历  对 列  (-1退出选择)");
            System.out.println("5---输出队列头元素  (-1退出选择)");
            System.out.println("6---输出队列尾元素  (-1退出选择)");
            System.out.println("7---退        出  (-1退出选择)");
            System.out.println("-------------------");
            System.out.print("请输入你的选择");
            int chooseNum = scanner.nextInt();
            switch (chooseNum){
                case 0:
                    if(queque.isFull()){
                        System.out.println("队列已满");
                        break;
                    }
                    System.out.println("队列未满");
                    break;
                case 1:
                    if(queque.isEmply()){
                        System.out.println("队列为空");
                        break;
                    }
                    System.out.println("队列未空");
                    break;
                case 2:
                    try{
                        System.out.println("请输入要添加的元素");
                        int num = scanner.nextInt();
                        queque.queueAdd(num);

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try{
                        System.out.println("请输入要删除的元素的序号");
                        int numOrder = scanner.nextInt();
                        queque.queueDelete(numOrder);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try{
                        queque.queuePrint();

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try{
                        System.out.println(queque.showFirstElem());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try{
                        System.out.println(queque.showEndElem());
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    bool = false;
                    scanner.close();
                    System.out.println("你已退出");

            }
        }


    }
}

class Queque {
    //queue中数组最大容量
    private int mixSize;
    //头指针
    private int front;
    //尾指针
    private int rear;
    //存放数据的数组
    private Integer[] queueArray;

    /**
     * 构造器
     *
     * @param mixSize 数组的容量的最大值
     *                front ,rear = -1,是queue的初始情况，此时数组是一个空的，
     */
    public Queque(int mixSize) {
        /*这个不设置的，下边用到的都是mixSize的默认的值0.
        mixSize是成员变量，一旦this.mixSize = mixSize
        整个类都可以用。
         */
        this.mixSize = mixSize;
        queueArray = new Integer[mixSize];
        front = -1;
        rear = -1;
    }

    /**
     * 队列为空
     *
     * @return ture表示队列为空
     */
    public boolean isEmply() {
        return front == rear;
    }

    /**
     * 队列为满
     *
     * @return ture队列为满
     */
    public boolean isFull() {
        return rear == mixSize - 1;
    }

    /**
     * 入对
     *
     * @param num 添加的数字
     */
    public void queueAdd(int num){
        //为什么要在添加数据前有queue是否为空的检查
        //判断queue是否为满，如果已满无法添加
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        rear++;
        queueArray[rear] = num;
        System.out.println("数据添加成功");
    }

    /**
     * 通过输入序号出队
     *
     * @param orderNum 数组的序号
     */
    public int  queueDelete(int orderNum){
        //如果queue为空，不能删除数。
        if(isEmply()){
            throw new RuntimeException("队列为空");
        }
        //在删除元素前检查orderNum是否有效
        if(orderNum > queueArray.length){
            throw new RuntimeException("queue的下标越界");
        }
        front++;
         int temp = queueArray[front];
        queueArray[front] = null;
        System.out.println("数据"+ temp + "出队成功");
        return temp;

    }

    /**
     * 遍历输出queue中的元素。
     */
    public void queuePrint(){
        //如果queue为空，无法遍历输出。
        if(isEmply()){
            throw new RuntimeException("队列为空");
        }
        for(int i = 0; i < queueArray.length; i++){
            System.out.println(queueArray[i]);
        }
    }

    /**
     * 读取队列的头元素
     *
     * @return 队列的头元素的值
     */
    public int showFirstElem(){
        //如果queue为空，无法输出
        if(isEmply()){
            throw new RuntimeException("队列为空");
        }
        return queueArray[front + 1];
    }

    /**
     * 读取队列的尾元素
     *
     * @return 队尾的元素
     */
    public int showEndElem(){
        if(isEmply()){
            throw new RuntimeException("队列为空");
        }
        return queueArray[rear];
    }

}