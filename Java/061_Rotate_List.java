// 一次遍历
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int len = 1;
        ListNode cur = head;
        while (cur.next != null){
            cur = cur.next;
            len++;
        }
        // 此时把链表头和尾链接起来
        cur.next = head;
        // 在往后走n - k % n个节点就到达新链表的头结点前一个点，这时断开链表即可
        int shift = len - k % len;
        for (int i = 0; i < shift; i++)
            cur = cur.next;
        
        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }
}
// faster than 100.00% of Java 