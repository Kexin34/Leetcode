// 归并排序
class Solution {
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    
    private ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)
            return head;
        //因为归并排序是一半一半的进行，所以需要快慢指针找到中点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //slow 刚好指向前边一半节点的最后一个节点
        ListNode head2 = slow.next;
        slow.next = null;
        //merge sort 核心过程：
        head = mergeSort(head);
        head2 = mergeSort(head2);
        return merge(head, head2); 
    }
    
    // 通用的merge two list函数
    private ListNode merge(ListNode l1, ListNode l2) {
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
        dummy.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
    
}

