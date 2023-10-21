package linkedlist;
//单链表：单链表中存放的是节点（数据域 和 指针域）
public class SingleLinkedListDemo {
    public static void main(String[]args){
        HeroNode hero1 = new HeroNode(1, "liz");
        HeroNode hero2 = new HeroNode(2, "peter");
        HeroNode hero3 = new HeroNode(3, "mary");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
try {
    singleLinkedList.Traverse();
}catch(Exception e){
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

    /**
     * 遍历单链表
     */
     public void Traverse(){
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
}



