// 思路：归并排序，找中点和合并操作

class Solution {
    public ListNode sortList(ListNode head) {
        // 递归结束条件
        if (head == null || head.next == null) 
            return head;
        
        //因为归并排序是一半一半的进行，所以需要快慢指针找到中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //slow 刚好指向前边一半节点的最后一个节点
        ListNode rightHead = slow.next;
        slow.next = null;
        
        //merge sort 核心过程：
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return mergeLists(left, right);
    }
    
    // 通用的merge two list函数
    private ListNode mergeLists(ListNode left, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null){
            if (left.val < right.val){
                cur.next = left;
                left = left.next;
            }
            else{
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = (left == null) ? right : left;
        return dummy.next;
    }
}
// faster than 98.23% of Java
// 时间复杂度O(nlogn)
