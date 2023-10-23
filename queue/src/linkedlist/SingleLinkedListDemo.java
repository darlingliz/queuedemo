package linkedlist;
//单链表：单链表中存放的是节点（数据域 和 指针域）
public class SingleLinkedListDemo {
    public static void main(String[]args){
        System.out.println("在尾部直接插入");
        HeroNode hero1 = new HeroNode(1, "liz");
        HeroNode hero4 = new HeroNode(4, "peter");
        HeroNode hero7 = new HeroNode(7, "mary");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero7);

try {
    singleLinkedList.traverse();
}catch(Exception e){
    System.out.println(e.getMessage());
}
        System.out.println("===========按顺序插入===========");
        HeroNode hero6 = new HeroNode(6, "liz");
        HeroNode hero2 = new HeroNode(2, "peter");
        HeroNode hero3 = new HeroNode(3, "mary");
        singleLinkedList.addById(hero6);
        singleLinkedList.addById(hero2);
        singleLinkedList.addById(hero3);
        singleLinkedList.addById(hero3);
        singleLinkedList.addById(hero6);

        try {
            singleLinkedList.traverse();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("========修改对应的node元素=========");
        HeroNode king = new HeroNode(1, "king");
        try {
            singleLinkedList.update(king);
            singleLinkedList.traverse();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

//单链表
class SingleLinkedList{
    //单链表的头元素，头元素不能动
     HeroNode head = new HeroNode(0,"");

    /**
     * 添加单链表的元素（在队尾加）
     * @param heroNode 要添加的元素
     */
     public boolean add(HeroNode heroNode){
         //temp临时的变量，通过temp的指针的移动找到队尾的元素
         HeroNode temp = head;
          while(true){
              //node的指针域为空，temp已经指向队尾的元素
              if(temp.next == null){
                  break;
              }
              //temp的指针向下移动
              temp = temp .next;
          }
          //把heroNode加在队尾
          temp.next = heroNode;
          return true;
     }

    /**按序号的顺序添加元素（序号相等无法添加）
     *
     * @param heroNode 要添加的元素
     */
    public void addById(HeroNode heroNode){
        //flag判断是否序号相同的标志
         Boolean flag = true;
         HeroNode temp = head;
         //通过循序找到要插入的元素的位置
         while(true){
             //要插入在尾部
             if(temp.next == null){
                 break;
             }
             /*
             因为本身这个就是有顺序的，所以只要大于即可找到位置
             是temp.next.id，如果是temp.id，也可找到位置，但是
             temp的位置已经在那个要插入的位置的后边了，就无法表示了。
              */
             if(temp.next.id > heroNode.id){
                 break;
             }
           //序号相等flag改变
             if(temp.next.id == heroNode.id){
                 flag = false;
                 break;
             }
             //没有找到对应的位置，temp指针向后移动。
             temp = temp.next;
         }
         if(flag == false){
             System.out.println(temp.next.id + "序号已存在，不能添加");
             return;
         }
         /*添加的元素在对应的位置。
         必须先是 heroNode.next = temp.next;
         因为temp.next可以表示添加元素的后一个位置，
         如果先改变的是temp.next = heroNode的话，
         那后一个元素就表示。
          */
         heroNode.next = temp.next;
         temp.next = heroNode;
    }

    /**
     * 遍历单链表
     */
     public void traverse(){
         HeroNode temp = head;
         //如果单链表只有头元素，无法遍历（头元素的数据域是不用的，只使用指针域）
         if(head.next == null){
            throw new RuntimeException("空链表，无法遍历");
         }
         //头元素的数据域中的数据没有意义，从第一个元素（头元素后边的）开始遍历
         temp = temp.next;
         while(true){
             //temp.next == null不行，这个条件会是 最后一个元素的数据域无法输出
             if(temp == null){
                 break;
             }
             System.out.println(temp);
             //temp的指针向后移动
             temp = temp.next;
         }
     }

    /**修改链表中的值(要修改的必须是id号相同的才能修改)
     *
     * @param heroNode
     */
    public void update(HeroNode heroNode){
         //如果单链表为空，无法修改
        if(head.next == null){
            throw  new RuntimeException("链表为空，修改失败");
        }
        //链表不为空，则可以从第一个元素开始
        HeroNode temp = head.next;
        //flag记录链表中的元素是否有可以修改的状态
        boolean  flag = true;
        while(true){
            if(temp.next == null){
                flag = false;
                break;
            }
            if(temp.id == heroNode.id){
                break;
            }
            //指针后移
            temp = temp.next;
        }
        if(flag == false){
            System.out.println("单链表中无此元素，无法修改");
            return;
        }
        temp.name = heroNode.name;
        System.out.println(temp.id + "修改成功");
    }
}



