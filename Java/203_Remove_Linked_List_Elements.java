// Method 1: 遍历(basic)

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;   //始终指向要删除的节点的前一个位置
        
        while (cur.next != null){
            ListNode next_ptr = cur.next;
            if (next_ptr.val == val) 
                cur.next = next_ptr.next;// 找到，remove next_ptr指向的node
            else
                cur = cur.next;
        }
        return dummy.next;
    }
}
// faster than 92.19% of Java



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
