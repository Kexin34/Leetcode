// 解法一：递归
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 思路：将链表翻转转化为一个子问题，然后通过递归方式依次解决
        // 先翻转两个，然后将后面的节点继续这样翻转，然后将这些翻转后的节点连接起来
        return reverse(head);
    }
    
    public ListNode reverse(ListNode node){
        if (node == null || node.next == null) return node;
        ListNode newList = node.next;   // tmp，保存下一阶段的头指针
        node.next = reverse(node.next.next);
        newList.next = node;
        return newList;
    }
}
// faster than 100.00% of Java
// Time : O(N) where N is the size of the linked list.
// Space: O(N) stack space utilized for recursion.


// 我写的递归
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = head.next;
        head.next = swapPairs(node.next);
        node.next = head;
        return node;
    }
}
// faster than 100.00% of Java
// Time : O(N) where N is the size of the linked list.
// Space: O(N) stack space utilized for recursion.






// 解法二：遍历
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        dummy.next = head;
        
        while (cur.next != null && cur.next.next != null){
            ListNode n1 = cur.next;
            ListNode n2 = cur.next.next;
            ListNode next = n2.next;       //下轮swap的头指针
            
            n1.next = next;
            n2.next = n1;
            cur.next = n2;      // 本轮swap后，前面连着第二个node
            cur = n1;
        }
        return dummy.next;
    } 
}
// faster than 100.00% of Java 
// Time : O(N) where N is the size of the linked list.
// Space Complexity : O(1).