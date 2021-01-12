// Method 1: 遍历(basic)

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;       //始终指向要删除的节点的前一个位置
        
        while (head != null) {
            if (head.val == val)
                pre.next = head.next;
            else
                pre = head;
            head = head.next;
        }
        return dummy.next;
    }
}
//faster than 100.00% of Java 



// Methos 2: 递归
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        if(head.val == val){
            return removeElements(head.next, val);  // skip这个node
        }else{
            head.next = removeElements(head.next, val);
            return head;
        }
    }
}
//faster than 85.27% of Java online
