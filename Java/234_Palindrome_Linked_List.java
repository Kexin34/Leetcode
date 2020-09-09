//两种方法思路其实是一样的

// 解法一：没有从中切断
class Solution {
    public boolean isPalindrome(ListNode head) {
        //先通过「双指针」中的快慢指针来找到链表的中点
        ListNode slow, fast;
        slow = fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow 指针现在指向链表中点
        //如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if (fast != null)
            slow = slow.next;
        
        //从slow开始反转后面的链表，现在开始比较回文串
        ListNode left = head;
        ListNode right = reverse(slow);
        while(right != null){
            if(left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;    
    }
    
    private ListNode reverse(ListNode head){
        ListNode pre = null, cur = head, next = head;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
//faster than 95.53% of Java


// Method 2: 从中切断
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;

        //通过双指针快慢遍历，找到中间node（之后用来cut成两半）
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null){   //stop at last node
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow 指针现在指向链表中点
        //如果fast指针没有指向null，说明链表长度为奇数，slow还要再前进一步
        if(fast != null) slow = slow.next;
        cut(head, slow);

        // 将后一半链表翻转，然后判断两半链表是否相等
        fast = reverse(slow);
        return isEqual(head, fast);
    }
    
    private void cut(ListNode head, ListNode cutNode){
        while(head.next != cutNode){
            head = head.next;
        }
        head.next = null;
    }

    // Helper function: reverse the linked list
    private ListNode reverse(ListNode head){
        ListNode newHead = null;
        while(head != null){
            ListNode nextNode = head.next;  //保存下一节点
            head.next = newHead;            //连接上建立好的倒叙链表开头
            newHead = head;                 //更新作为下一次循环被接上的开头
            head = nextNode;                //去下一个node
        }
        return newHead;
    }
    
    //check are the two "same length" linked list equal
    private boolean isEqual(ListNode l1, ListNode l2){
        while(l1 != null && l2 != null){
            if(l1.val != l2.val) 
                return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
// // faster than 97.18% of Java online