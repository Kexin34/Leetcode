/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// Method #1: 基础hashmap解法，遍历两遍链表
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        
        //遍历第一遍链表，不考虑链表之间的相互关系，仅仅生成所有节点，然后把它存到 HashMap中
        // hashmap中存着<原表对应node，新表对应node>
        HashMap<Node, Node> map = new HashMap<>();
        Node h = head;
        while(h != null){
            Node t = new Node(h.val); //生成节点
            map.put(h, t);
            h = h.next;
        }
        
        //遍历第二遍链表，将之前生成的节点取出来，更新它们的 next 和 random 指针。
        h = head;
        while(h != null){
            if(h.next != null)
                map.get(h).next = map.get(h.next);//让新表的现node指向新表它的nextnode
            if(h.random != null)
                map.get(h).random = map.get(h.random);
            h = h.next;
        }
        return map.get(head);
    }
}
// faster than 100.00% of Java 


// Method 1 的优化，只需要遍历一次
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        
        HashMap<Node, Node> map = new HashMap<>();
        Node h = head;
        Node cur = new Node(-1); //空结点，dummy 节点，为了方便头结点计算
        while(h != null){
            //判断当前节点是否已经产生过,若无，放入
            if(!map.containsKey(h)){
                Node t = new Node(h.val);
                map.put(h, t);
            }
            //得到当前新链节点去更新它的 random 指针
            Node next = map.get(h);
            if(h.random != null){
                //判断当前节点随机指向的节点是否已经产生过
                if(!map.containsKey(h.random)){
                    //若无，把指向的随机node复制一个，加入hashmap
                    next.random = new Node(h.random.val);
                    map.put(h.random, next.random);
                }else
                    //若已有，直接在新链的node随机指针后面接上
                    next.random = map.get(h.random);
            }
            //将当前生成的节点接到 cur 的后边
            cur.next = next;
            cur = cur.next;
            h = h.next;
        }
        return map.get(head);
    }
}
// faster than 100.00% of Java










