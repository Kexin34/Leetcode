// 解法一：简单粗暴，把链表存储到线性表中，然后用双指针依次从头尾取元素即可。
class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        
        //把所有node存到 list 中去
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        
        //头尾指针依次取元素
        int i = 0, j = list.size() - 1;
        while(i < j){
            list.get(i).next = list.get(j);
            i++;
            //偶数个节点的情况，会提前相遇
            if(i == j) break;
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(j).next = null;
    }
}
// faster than 43.56% of Java



//解法二：（优化）遍历
// 找到中点断开，翻转后面部分，然后合并前后两个链表
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
    
    // 子函数: 合并两个链表 (这个写法直接建立在l1上，不需要新建list，更好)
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
        if (l2 != null) {
            l1.next = l2;
        }
        l1 = head;
    }
    
    // 翻转链表的子函数
    private ListNode reverse(ListNode head){
        ListNode newHead = null;
        while (head != null){
            ListNode nextNode = head.next;
            head.next = newHead;
            newHead = head;
            head = nextNode;
        }
        return newHead;
    }
}

/*  我自己写的mergeLists子函数，有点啰嗦，但是一样好用

    private void mergeLists(ListNode left, ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null){
            cur.next = left;
            left = left.next;
            cur = cur.next;
            
            cur.next = right;
            right = right.next;
            cur = cur.next;
        }
        cur.next = (left == null) ? right : left;
        left = dummy.next;
    }
*/

// faster than 99.95% of Java




// ****** 完全没看懂的递归解法**************
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null)
            return;
        int len = 0;
        ListNode h = head;
        /* 求出节点数 */
        while(h != null){
            len++;
            h = h.next;
        }
        reorderListHelper(head, len);
    }
    
    private ListNode reorderListHelper(ListNode head, int len){
        //递归出口的话，如果只有一个节点，那么我们只需要将 head.next 返回。
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        //如果是两个节点，我们需要将 head.next.next 返回
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }
}

