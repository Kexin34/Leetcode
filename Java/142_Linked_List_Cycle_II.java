// 给定一个链表，返回链表开始入环的第一个节点。  如果链表无环，则返回  null。
// 思路：快慢指针，快慢相遇之后，慢指针回到头，快慢指针步调一致一起移动，相遇点即为入环点
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head.next;
        
        while (fast != null && fast.next != null){
            if (fast == slow){// 第一次相遇，slow回到head, fast从相遇点下一个节点开始走，
                slow = head;
                fast = fast.next;
                
                while (fast != slow){// 第二次相遇的地方就是环的入口, 返回
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
            
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }
}
// faster than 100.00% of Java
// 时间复杂度 O(N)：第二次相遇中，慢指针须走步数 a < a + b；第一次相遇中，
//          慢指针须走步数 a + b - x < a + b，其中 x为双指针重合点与环入口距离；因此总体为线性复杂度；
// 空间复杂度 O(1) ：双指针使用常数大小的额外空间。
