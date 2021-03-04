//典型的基于链表的插入排序，遍历

cclass Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);//哨兵节点
        ListNode cur = head;     // 每次遍历要被插入的节点
        ListNode pre = dummy;    //insert node between pre and pre.next
        ListNode next = null;      //下一次要插入的节点
        
        //遍历每个node，not the end of input list
        while (cur != null){
            next = cur.next;    // 保留当前节点的next节点

            //find the right place to insert(prev.next 要> inserted node)
            while (pre.next != null && pre.next.val < cur.val)
                pre = pre.next;
            
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            
            //update for next interation
            pre = dummy;
            cur = next;
        }
        return dummy.next;
    }
}