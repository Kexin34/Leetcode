
//recursive way
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        //开始递归
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
//faster than 100.00% of Java 


//Approach 2: Iteration
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);    // 哨兵节点
        ListNode dummy = prehead;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                dummy.next = new ListNode(l2.val);
                l2 = l2.next;  
            }else{
                dummy.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            dummy = dummy.next;
        }
        // exactly one of l1 and l2 can be non-null at this point
        // the non-null list to the end of the merged list.
        dummy.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}