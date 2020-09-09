/*
链表相关的核心点:
	null/nil 异常处理
	dummy node 哑巴节点
	快慢指针
	插入一个节点到排序链表
	从一个链表中移除一个节点
	翻转链表
	合并两个链表
	找到链表的中间节点

哑巴节点使用场景:
	当头节点不确定的时候，使用哑巴节点

*/


// 83. Remove Duplicates from Sorted List
// 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
// 1. 非递归解法
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
// 2. 递归解法
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



// 82. Remove Duplicates from Sorted List II
// 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中   没有重复出现的数字。
// 思路：链表头结点可能被删除，所以用 dummy node 辅助删除
// 双指针解法
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) 
            return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head.next;
        
        while (fast != null){
            // 暂时无重复，快慢指针一起向前移动
            if (fast.val != slow.next.val){
                slow = slow.next;
                fast = fast.next;
            }else{  
                // 快指针和慢next node重复，让fast先前进到不重复的节点，再让慢next指向fast
                while (fast != null && fast.val == slow.next.val)
                    fast = fast.next;
                slow.next = fast;
                fast = fast == null ? null : fast.next;
            }
        }
        return dummy.next;
    }
}



// 206. Reverse Linked List
// 反转一个单链表。
// 思路：用一个 newHead 做头结点，将链表依次插到头结点后面。头插法
// 1. 遍历（头插法）
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = new ListNode (-1);
        while (head != null){
            ListNode next = head.next;  // 保存下一个node连接
            head.next = newHead.next;   // 只需改变指针，不需创新新节点
            newHead.next = head;        // 头结点next指向新插入节点
            head = next;                // resume指向原list下一个节点
        }
        return newHead.next;
    }
}
// 2. 递归
class Solution {
    public ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) return head;
        
        // 这里cur就是最后一个节点
        ListNode cur = reverseList(head.next);

        // 如果原链表是1->2->3->4->5，这时cur就是5
        // 而head是4，head的下一个是5，下下一个是null
        // 所以，head.next.next指向就应该改成5 -> 4
        head.next.next = head;
        
        // 防止链表cycle，需要把head.next设置为空
        head.next = null;
        // 每层递归函数都返回cur，即最后一个节点
        return cur;
    }
}


// 92. Reverse Linked List II
// 反转从位置  m  到  n  的链表。请使用一趟扫描完成反转。
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


// 21. Merge Two Sorted Lists
// 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
// 1. 递归
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        //开始递归
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
// 2.遍历
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }
            else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}






//86. Partition List
// 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于  x  的节点都在大于或等于  x  的节点之前。
// 思路：构造两个链表，beforeHead小于x的节点，afterHead大于x的节点，最后将他们连在一起
class Solution {
    public ListNode partition(ListNode head, int x) {
        // 构造两个链表，beforeHead小于x的节点，afterHead大于x的节点，最后将他们连在一起
        // 这里的技巧就是给两个链表都搞一个哑巴节点，也就是指向头结点的节点
        ListNode beforeHead = new ListNode(-1);
        ListNode afterHead = new ListNode(-1);
        ListNode before = beforeHead;
        ListNode after = afterHead;
        
        while (head != null){
            if (head.val < x){
                before.next = head;
                before = before.next;
            }else{
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        // 拼接两个lists
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }
}


// 148. Sort List
// 在  O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
//思路：归并排序，找中点和合并操作
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




// 143. Reorder List
// 思路：找到中点断开，翻转后面部分，然后合并前后两个链表
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        ListNode slow = head, fast = head.next;
        // 找到链表中点
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        fast = reverse(slow.next);  // 将后一半链表翻转, 用fast记录后一段的头结点
        slow.next = null;           // 将链表切断
        mergeLists(head, fast);     // 合并前后两个链表
    }
    
    // 子函数: 合并两个链表
    private void mergeLists(ListNode l1, ListNode l2) {
        ListNode head = l1, temp;
        while (l1 != null && l2 != null) {
            temp = l2.next;
            l2.next = l1.next;
            l1.next = l2;
            l2 = temp;
            l1 = l1.next.next;
        }
        // 将未合并完的l2接到l1后面
        if (l2 != null) l1.next = l2;
        l1 = head;
    }
    
    // 翻转链表的子函数
    private ListNode reverse(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode nextNode = head.next;  // 保存下一个节点
            head.next = newHead;            // 连接上建立好的倒叙链表开头
            newHead = head;                 // 更新作为下一次循环被接上的开头
            head = nextNode;                //去处理顺序下一个node
        }
        return newHead;
    }
}



// 141. Linked List Cycle
// 给定一个链表，判断链表中是否有环。
// 思路：快慢指针，快慢指针相同则有环，如果有环每走一步快慢指针距离会减 1，最终重合。
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head;

        while (slow.next != null && fast.next != null){
            slow = slow.next;
            if (fast.next.next != null)
                fast = fast.next.next;
            else
                return false;   // fast走到底了都没遇到slow，无环

            if (slow == fast)   // 一旦重合，立刻返回有环
                return true;
        }
        return false;
    }
}



// 142. Linked List Cycle II
// 给定一个链表，返回链表开始入环的第一个节点。  如果链表无环，则返回  null。
// 思路：快慢指针，快慢相遇之后，慢指针回到头，快慢指针步调一致一起移动，相遇点即为入环点
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head.next;
        
        while (fast != null && fast.next != null){
            if (fast == slow){// 第一次相遇，slow回到head, fast从相遇点下一个节点开始走，
                slow = head;
                fast = fast.next;
                // 第二次相遇的地方就是环的入口, 返回
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }
}



// 234. Palindrome Linked List
// 请判断一个链表是否为回文链表。
// 切成两半，把后半段反转，然后比较两半是否相等。
// 请判断一个链表是否为回文链表。
// 切成两半，把后半段反转，然后比较两半是否相等。
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        
        // 快慢指针，找到链表中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 若为偶数个节点，往后走一个，确保slow是指向第二段开头处
        if (fast != null) 
            slow = slow.next;
        
        // 将链表切成两半，
        cut(head, slow); 
        // 将后一半链表翻转，然后判断两半链表是否相等
        fast = reverse(slow);
        return isEqual(head, fast);
    }
    
    private boolean isEqual(ListNode l1, ListNode l2){
        while (l1 != null && l2 != null){
            if (l1.val != l2.val)
                return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode nextNode = head.next;  // 保存下一个节点
            head.next = newHead;            // 连接上建立好的倒叙链表开头
            newHead = head;                 // 更新作为下一次循环被接上的开头
            head = nextNode;                //去处理顺序下一个node
        }
        return newHead;
    }
    
    // 把head链表从cutNode处断开（不含有cutNode），只需要添加null在第一段末尾
    private void cut(ListNode head, ListNode cutNode){
        while (head.next != cutNode)
            head = head.next;
        head.next = null;
    }
}





// 138. Copy List with Random Pointer
// 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。 
// 要求返回这个链表的 深拷贝。























