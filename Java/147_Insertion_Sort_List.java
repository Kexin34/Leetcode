//典型的基于链表的插入排序，遍历

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode prehead = new ListNode(-1);//哨兵节点
        ListNode cur = head;        //the node will be inserted
        ListNode pre = prehead;     //insert node between pre and pre.next
        ListNode next = null;       //下一个要插入的节点
        
        //遍历每个node，not the end of input list
        while(cur != null){
            next = cur.next;
            
            //find the right place to insert(prev.next bigger than inserted node)
            while(pre.next != null && pre.next.val < cur.val)
                pre = pre.next;
            
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            
            //update for next interation
            pre = prehead;  //reset starter
            cur = next;
        }
        return prehead.next;
    }
}
