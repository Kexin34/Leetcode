// 解法：两次遍历
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy;
        int len = 0;
        
        // 第一次遍历，找出长度
        while (first != null){
            first = first.next;
            len++;
        }
        len--;  //len不包括dummy node，所以减一
        
        // 第二次遍历，停在待删除node前面一个（停在L-n）
        first = dummy;
        for (int i = 0; i < len - n; i++){//-1因为
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }
}



// 解法：双指针
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        for (int i = 0; i < n + 1; i++)
            fast = fast.next;
        
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
// faster than 100.00% of Java
// Time: O(L), numbers of nodes
// Space: O(1)