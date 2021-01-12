// 解法一：非递归
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null){
            if (curr.val == curr.next.val)  //有重复
                curr.next = curr.next.next; // 改变指针，跳过next node
            else
                curr = curr.next;
        }
        return head;
    }
}
// faster than 100.00% of Java
//Time complexity : O(n). 
// Space complexity : O(1). No additional space is used.


// 解法二：递归
// 1.我写的递归
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        if (head.val == head.next.val)
            return deleteDuplicates(head.next);
        else{
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
//  faster than 100.00% 

// 2. 别人写的递归
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 终止条件：到达末尾
        if (head == null || head.next == null) 
            return head;
        
        // 本node的next指针连着递归放回的node
        head.next = deleteDuplicates(head.next);
        
        // 如果本node和下一个node重复，返回下一个node（跳过本node）接回调用的next指针
        return head.val == head.next.val ? head.next : head;
    }
}
// faster than 100.00% of Java 


// 解法三： 双指针
// 让fast指针每次指向下一个不同val的节点，然后让slow.next连接上fast
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head.next;
        
        while(fast != null){
            if(fast.val != slow.val){
                slow.next = fast;
                slow = slow.next;   // slow++;
            }
            fast = fast.next;       // fast++
        }
        // 断开与后面重复元素的连接
        slow.next = null;
        return head;
    }
}
//faster than 100.00% of Java