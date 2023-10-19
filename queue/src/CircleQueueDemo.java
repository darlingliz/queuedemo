import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
        int maxSize = 4;
        boolean bool = true;
        CricleQueue cricleQueue = new CricleQueue(maxSize);
        Scanner scanner = new Scanner(System.in);
        System.out.println(maxSize);

        while (bool){
       System.out.println("-----------------------");
            System.out.println("0---判queue是否满 ");
            System.out.println("1---判queue是否空  ");
            //我想让在进入选项的时候有反悔的功能，但要是要添加的数就是-1怎么办？
            System.out.println("2---元  素  入 队  (-1退出选择)");
            System.out.println("3---元  素  出 队  (-1退出选择)");
            System.out.println("4---遍  历  对 列  (-1退出选择)");
            System.out.println("5---输出队列头元素  (-1退出选择)");
            System.out.println("6---退        出  (-1退出选择)");
            System.out.println("-------------------");
            System.out.print("请输入你的选择");
            int choice = scanner.nextInt();
            switch (choice){
                case 0:
                    if(cricleQueue.isFull()){
                        System.out.println("队列已满");
                        break;
                    }
                    System.out.println("队列未满");
                    break;
                case 1:
                    if(cricleQueue.isEmpty()){
                        System.out.println("队列已空");
                        break;
                    }
                    System.out.println("队列未空");
                    break;
                case 2:
                    System.out.println("请输入你要添加的数");
                    int num = scanner.nextInt();
                    try{
                    if(cricleQueue.queueAdd(num)) {
                        System.out.println("添加成功");
                        break;
                    }
                    //除了队列已满，还会有是添加失败的例子吗？
                    System.out.println("添加失败");
                    break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }


                case 3:
                    try{
                        int i = cricleQueue.queueDelete();
                        System.out.println("出队的元素是" + i);
                        break;

                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                case 4:
                    try {
                        cricleQueue.everyElem();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                }
                    break;

                case 5:
                    try{
                    int i = cricleQueue.firstElem();
                    System.out.println("队列的头元素是" + i);
                    break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }

                case 6:
                    System.out.println("你确定要退出吗？（y / n）");
                    char c = scanner.next().charAt(0);
                    if(c == 'y'){
                        System.out.println("成功退出");
                        bool = false;
                        break;
                    }
                    System.out.println("放弃退出");
            }

        }

    }
}

/*

 */
class CricleQueue {
    private int front;
    private int rear;
    private int[] array;
    private int mixSize;

    public CricleQueue(int mixSize) {
        this.mixSize = mixSize;
        array = new int[mixSize];
    }

    /**
    *队满的操作
     * @return （true 队列为满）
     */
    public boolean isFull() {
        return (rear + 1) % mixSize == front;
    }


    /**
     * 队空的操作
     * @return （true 队列为空）
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**入队的操作
     *
     * @param addElem 入队的元素
     * @return （true）入队成功
     */
    public boolean queueAdd(int addElem) {
        //如果对满，则不能添加
        if (isFull()) {
            //队列满时选择了抛出异常
          throw new RuntimeException("队列已满，入队失败");
        }
        array[rear] = addElem;
        rear = (rear + 1) % mixSize;
        return true;
    }

    /**出队的操作(序号)
     *
     * @return 出队的元素
     * ？？出队必须按顺序吗？可以自己输入序号，直接的按序号出队吗？
     */
    public int queueDelete() {
        if (isEmpty()) {
            /*
            出队想要出队的元素，同时不想使用if{}else{}
            所以抛出异常
             */
        throw new RuntimeException("队列为空，出队失败");
        }
            int temp = array[front];
            front = (front + 1) % mixSize;
            return temp;
        }


    /**
     * 队列元素个数
     * @return 元素个数
     */
    public int queueCount() {
        /* 如果rear在front的下边的时候，
        无法使用rear - front
         */
        return (rear + mixSize - front) % mixSize;
    }

    /**遍历队列中的元素
     *
     */
    public void everyElem() {
        if(isEmpty()){
            throw  new RuntimeException("队列为空，不能遍历元素");
        }
        /*
        front指向的是第一个元素，所以i的初值为front
        i< array.length X 因为队列中的出队并不是真正的
        出队，只是front的指针不在指向，如果遍历的是整个的
        数组的长度的时候会输出出队的元素。
         */
        for (int i = front; i < front + queueCount(); i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    /**取出队头元素
     *
     * @return 头元素
     */
    public int firstElem() {
        if(isEmpty()){
            throw new RuntimeException("队列为空，无法取出头元素");
        }
        return array[front];
    }
}
