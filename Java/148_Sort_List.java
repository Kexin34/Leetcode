// 解法1：归并排序，找中点和合并操作
// Time complexity: O(nlogn)
// Space complexity: O(logn)

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
        //slow 刚好指向前边一半节点的最后一个节点,记得要断开！！
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


// 解法二：快速排序
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 让pivot为中间点
        ListNode mid = findMidPoint(head);
        
        //把当前分为三段（大于，等于，小于）
        ListNode leftDummy = new ListNode(-1), leftTail = leftDummy;
        ListNode rightDummy = new ListNode(-1), rightTail = rightDummy;
        ListNode middleDummy = new ListNode(-1), middleTail = middleDummy;
        
        while (head != null) {
            if (head.val < mid.val){
                leftTail.next = head;
                leftTail = leftTail.next;
            }else if (head.val > mid.val){
                rightTail.next = head;
                rightTail = rightTail.next;
            }else{
                middleTail.next = head;
                middleTail = middleTail.next;
            }
            head = head.next;
        }
        leftTail.next = null;
        rightTail.next = null;
        middleTail.next = null;
        
        // 三段形成好了，接下来把左右两段递归
        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);
        
        return merge(left, middleDummy.next, right);
    }
    
    private ListNode findMidPoint(ListNode head){
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private ListNode merge(ListNode left, ListNode middle, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        tail.next = left; tail = getTail(tail);
        tail.next = middle; tail = getTail(tail);
        tail.next = right;     tail = getTail(tail);
        return dummy.next;
    }
    
    private ListNode getTail(ListNode head) {
        if (head == null) return null;
        while (head.next != null)
            head = head.next;
        return head;
    }
}
// Runtime: 7 ms, faster than 47.41% of Java