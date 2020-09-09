// 解法：遍历
// 思路：先遍历到 m 处，翻转，再拼接后续，注意指针处理
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);  // 哑节点，指向链表的头部
        dummy.next = head;
        ListNode pre = dummy;           // pre 指向要翻转子链表的第一个节点
        
        for (int i = 1; i < m; i++)
            pre = pre.next;
        
        head = pre.next;        // head指向翻转子链表的首部
        ListNode next;
        for (int i = m; i < n; i++){
            next = head.next;
            // head节点连接next节点之后链表部分，也就是向后移动一位
            head.next = next.next;
            // next节点移动到需要反转链表部分的首部
            next.next = pre.next;
            // pre继续为需要反转头节点的前驱节点
            pre.next = next;
        }
        return dummy.next;
    }
}
//  faster than 100.00% of Java



// 解法：递归
class Solution {
    ListNode successor = null;  // 后驱节点

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // base case, 相当于反转前 n 个元素
        if (m == 1)         
            return reverseN(head, n);
        
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
            return head;
    }
    
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n){
        if(n == 1){
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head; //翻转指针
        // 让反转之后的 head 节点和后面的节点连起来（后驱节点）
        head.next = successor;
        return last;
    }
}