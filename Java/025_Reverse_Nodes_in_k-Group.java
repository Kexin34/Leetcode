class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for(int i = 0; i < k; i++){
            // 不足 k 个，不需要反转，base case
            if(b == null) return head;
            b = b.next;
        }
        // 反转前 k 个元素 [a,b)
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b,k);
        return newHead;
    }
    
    // 迭代实现reverse 函数反转一个区间之内的元素。给定链表头结点，反转整个链表
    private ListNode reverse(ListNode a){// 反转以 a 为头结点的链表
        ListNode pre, curr, next;
        pre = null; curr = a; next = a;
        while(curr != null){
            next = curr.next;
            // 逐个结点反转
            curr.next = pre;
            // 更新指针位置
            pre = curr;
            curr = next;
        }
        // 返回反转后的头结点(这时curr = null，返回前一个)
        return pre;
    }  
    
    /** 迭代,反转区间 [a, b) 的元素，注意是左闭右开 */
    private ListNode reverse(ListNode a, ListNode b){
        ListNode pre, curr, next;
        pre = null; curr = a; next = a;
        // while 终止的条件改一下就行了
        while(curr != b){
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}

//faster than 100.00% of Java 