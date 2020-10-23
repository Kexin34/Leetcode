// 分别遍历两个链表，得到分别对应的长度。
// 然后求长度的差值，把较长的那个链表向后移动这个差值的个数，然后一一比较即可。
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = getLength(headA), lenB = getLength(headB);
        
        if (lenA > lenB)
            for (int i = 0; i < lenA - lenB; i++) headA = headA.next;
        else
            for (int i = 0; i < lenB - lenA; i++) headB = headB.next;
        // 如有交叉点，将会停在交叉点
        while (headA != null && headB != null && headA != headB){
            headA = headA.next;
            headB = headB.next;
        }
        return (headA != null && headB != null) ? headA : null;
    }
    
    private int getLength(ListNode head){
        int cnt = 0;
        while (head != null){
            head = head.next;
            cnt++;
        }
        return cnt;
    }
}
//  faster than 99.27% of Java
// Time:O(n), 只需要将最短链表遍历两次即可
// Space:O(1)