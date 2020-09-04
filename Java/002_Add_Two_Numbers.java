class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        int carry = 0;
        
        while (c1 != null || c2 != null){
            int d1 = c1 == null ? 0 : c1.val;
            int d2 = c2 == null ? 0 : c2.val;
            int sum = d1 + d2 + carry;
            
            carry = sum >= 10 ? 1 : 0;
            cur.next = new ListNode(sum % 10);
            
            cur = cur.next;
            if (c1 != null) c1 = c1.next;
            if (c2 != null) c2 = c2.next;
        }
        
        //末尾如果想加为10要进位，即最后再添加一
        if (carry == 1) 
            cur.next = new ListNode(1);
        
        return dummy.next;
    }
}
// faster than 73.06% of Java
// Time : O(m + n), lenght of both lists