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