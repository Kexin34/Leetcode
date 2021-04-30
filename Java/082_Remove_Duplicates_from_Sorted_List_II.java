// 用两个指针，慢指针永远指着非重复的数, 快指针永远指着最后一个重复的数。

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy, fast = head;
        slow.next = head;
        
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val)
                fast = fast.next;
            
            // 若快慢指针不相邻，慢指针直接直到快指针下一个（跳过重复）
            if (slow.next != fast){
                slow.next = fast.next;
                fast = fast.next;
            }else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java




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