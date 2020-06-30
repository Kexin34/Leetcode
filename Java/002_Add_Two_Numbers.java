class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);//哨兵节点
        ListNode dummy = sentinel;
        int sum = 0;
        //process 共同位数
        while(c1 != null || c2 != null){
            sum /= 10;      //check carry
            if(c1 != null){
                sum += c1.val;
                c1 = c1.next;
            }
            if(c2 != null){
                sum += c2.val;
                c2 = c2.next;
            }
            dummy.next = new ListNode(sum % 10);
            dummy = dummy.next;
        }
        //末尾如果想加为10要进位，即最后再添加一
        if(sum / 10 == 1)
            dummy.next = new ListNode(1);
        return sentinel.next;
    }
}

//faster than 19.95% of Java