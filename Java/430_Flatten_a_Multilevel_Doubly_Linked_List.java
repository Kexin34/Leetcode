/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
// 解法：迭代（最优）
class Solution {
    public Node flatten(Node head) {
        if( head == null) return head;
        
        Node cur = head;
        while (cur != null){
            /* CASE 2: got child, find the tail of the child and link it to p.next */
            if (cur.child != null){
                Node next = cur.next;
                Node last = cur.child;
                // Find the tail of the child
                while (last.next != null) last = last.next;
                
                // Connect p with p.child, and remove p.child
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;  
                
                // Connect tail with original next, if it is not null
                last.next  = next;
                if (next != null) next.prev = last; 
            }
            /* CASE 1: if no child, proceed */
            cur = cur.next;
        }
        return head;
        
    }
}
// faster than 100.00% of Java



// DFS 递归（非最优）
class Solution {
    public Node flatten(Node head) {
        Node cur = head;
        while (cur != null){
            // 没有子结点的结点就保持原状，不作处理。
            // 有子结点的，由于子结点链接的双向链表要加到后面，所以当前结点之后要断开
            // 在断开之前，我们用变量 next 指向下一个链表，然后对子结点调用递归函数
            if (cur.child != null){
                Node next = cur.next;
                cur.child = flatten(cur.child);
                // 我们 suppose 返回的结点已经压平了,把这一层的结点加到断开的地方
                // 开始链接, 把子结点链到 cur 的 next，然后把反向指针 prev 也链上。
                Node last = cur.child;
                while (last.next != null) last = last.next;
                cur.next = cur.child;
                cur.next.prev = cur;
                cur.child = null;   // 此时cur的子结点 child 可以清空
                // 压平的这一层的末节点last链上之前保存的next结点，如果 next 非空，那么链上反向结点 prev。
                last.next  = next;
                if (next != null) next.prev = last;
            }
            cur = cur.next;
        }
        return head;
        
    }
}
// faster than 25.57% of Java