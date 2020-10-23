
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next;  //尾指针
        ListNode evenHead = even;       // 保存偶节点起始位置
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
//faster than 100.00% of Java 
// Time  : O(n). There are total nn nodes and we visit each node once.
//space  : O(1). All we need is the four pointers.

