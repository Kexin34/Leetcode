// 解法一：遍历（头插法）
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = new ListNode (-1);
        while (head != null){
            ListNode next = head.next;  // 保存下一个node连接
            head.next = newHead.next;   // 只需改变指针，不需创新新节点
            newHead.next = head;        // 头结点next指向新插入节点
            head = next;                // resume指向原list下一个节点
        }
        return newHead.next;
    }
}
// faster than 100.00% of Java


// 解法二：递归
// 返回的cur一直都是原list最后一个节点，但中途是一直在翻转head和head.next的指向
class Solution {
    public ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) return head;
        
        // 这里cur就是最后一个节点
        ListNode cur = reverseList(head.next);

        // 如果原链表是1->2->3->4->5，这时cur就是5
        // 而head是4，head的下一个是5，下下一个是null
        // 所以，head.next.next指向就应该改成5 -> 4
        head.next.next = head;
        
        // 防止链表cycle，需要把head.next设置为空
        head.next = null;
        
        // 每层递归函数都返回cur，即最后一个节点
        return cur;
    }
}
// faster than 100.00% of Java