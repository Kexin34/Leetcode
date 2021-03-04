//思路：快慢指针
// 快慢指针相同则有环，如果有环每走一步快慢指针距离会减 1，最终重合。
// Time complexity: O(n)
// Space complexity: O(1)

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;

        while (slow.next != null && fast.next != null){
            slow = slow.next;
            if (fast.next.next != null)
                fast = fast.next.next;
            else
                return false;   // fast走到底了都没遇到slow，无环
            
            if (slow == fast)   // 一旦重合，立刻返回有环
                return true;
        }
        return false;
    }
}
//faster than 100.00% of Java 