package linkedlist;

/**
 * HeroNode链表中存放的节点类
 */
public class HeroNode {
        public int id;
        public String name;
        linkedlist.HeroNode next;
        public HeroNode(int id, String name){
            this.id = id;
            this.name = name;
        }
        public String toString(){
            return "id = " + id + " " +
                    "name = " + name;
        }
}
