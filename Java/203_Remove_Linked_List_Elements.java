// Method 1: 遍历(basic)
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        ListNode dummy = prehead;
        // dummy 始终指向要考虑的节点的前一个位置
        while(dummy.next != null){
            ListNode next_ptr = dummy.next;
            if(next_ptr.val == val)
                // 找到，remove next_ptr指向的node
                dummy.next = next_ptr.next;
            else
                dummy = dummy.next;
        }
        return prehead.next;
    }
}
//faster than 85.27% of Java 

// Methos 2: 递归
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        if(head.val == val){
            return removeElements(head.next, val);  
        }else{
            head.next = removeElements(head.next, val);
            return head;
        }
    }
}
//faster than 85.27% of Java online
