// 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于  x  的节点都在大于或等于  x  的节点之前。
// 思路：构造两个链表，beforeHead小于x的节点，afterHead大于x的节点，最后将他们连在一起

// 双指针，left指针用来串起来所有小于x的结点， right指针用来串起来所有大于等于x的结点。
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        
        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);
        ListNode left = leftDummy, right = rightDummy;
        
        while (head != null) {
            if (head.val < x){
                left.next = head;
                left = left.next;
            }else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        left.next = rightDummy.next;
        right.next = null;
        return leftDummy.next;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 