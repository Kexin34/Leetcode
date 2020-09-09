// 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于  x  的节点都在大于或等于  x  的节点之前。
// 思路：构造两个链表，beforeHead小于x的节点，afterHead大于x的节点，最后将他们连在一起

class Solution {
    public ListNode partition(ListNode head, int x) {
        // 构造两个链表，beforeHead小于x的节点，afterHead大于x的节点，最后将他们连在一起
        // 这里的技巧就是给两个链表都搞一个哑巴节点，也就是指向头结点的节点
        ListNode beforeHead = new ListNode(-1);
        ListNode afterHead = new ListNode(-1);
        ListNode before = beforeHead;
        ListNode after = afterHead;
        
        while (head != null){
            if (head.val < x){
                before.next = head;
                before = before.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        // 拼接两个list
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }
}
// faster than 100.00% of Java 