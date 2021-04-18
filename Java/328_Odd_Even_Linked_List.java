
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
// faster than 100.00% of Java 
// Time  : O(n). There are total nn nodes and we visit each node once.
// space  : O(1). All we need is the four pointers.
// 空间最优（符合follow up）Memory Usage: 38.1 MB, less than 99.70% of Java


// 自己写的啰嗦，不符合follow up
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);
        ListNode odd = oddDummy;
        ListNode even = evenDummy;
        int cnt = 1;
        while (head != null){
            if (cnt % 2 == 1){//odd idx
                odd.next = head;
                odd = odd.next;
            }else{
                even.next = head;
                even = even.next;
            }
            cnt++;
            head = head.next;
        }
        odd.next = evenDummy.next;
        even.next = null;
        return oddDummy.next;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java