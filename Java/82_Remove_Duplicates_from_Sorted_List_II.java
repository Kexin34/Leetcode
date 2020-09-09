// 思路：链表头结点可能被删除，所以用 dummy node 辅助删除
// 注意点:
// • A->B->C 删除 B，A.next = C 
// • 删除用一个 Dummy Node 节点辅助（允许头节点可变） 
// • 访问 b.next一定要保证 X != null

// 解法：双指针（快慢指针）
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) 
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head.next;
        
        while (fast != null){
            // 暂时无重复，快慢指针一起向前移动
            if (fast.val != slow.next.val){
                slow = slow.next;
                fast = fast.next;
            }else{  
                // 快指针和慢next node重复，让fast先前进到不重复的节点，再让慢next指向fast
                while (fast != null && fast.val == slow.next.val)
                    fast = fast.next;
                slow.next = fast;
                fast = fast == null ? null : fast.next;
            }
        }
        return dummy.next;
    }
}
// faster than 100.00% of Java