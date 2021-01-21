// 解法一：暴力法：如果可以逆序处理为前提
// 先把两个input逆序，这样从可以从低位到高位计算，结束后再逆序一次，返回
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1New = reverseList(l1);
        ListNode l2New = reverseList(l2);
        return reverseList(add(l1New, l2New));
    }
    
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
            
        }
        return prev;
    }
     public ListNode add(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode c1 = l1;
        ListNode c2 = l2;
        int carry = 0;
        
        while (c1 != null || c2 != null){
            int d1 = c1 == null ? 0 : c1.val;
            int d2 = c2 == null ? 0 : c2.val;
            int sum = d1 + d2 + carry;
            
            carry = sum >= 10 ? 1 : 0;
            cur.next = new ListNode(sum % 10);
            
            cur = cur.next;
            if (c1 != null) c1 = c1.next;
            if (c2 != null) c2 = c2.next;
        }
        
        //末尾如果想加为10要进位，即最后再添加一
        if (carry == 1) 
            cur.next = new ListNode(1);
        
        return dummy.next;
    }
}


// Follow-up：不让翻转列表
// 解法二：（最优）利用栈来做逆序处理
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = buildStack(l1);
        Stack<Integer> l2Stack = buildStack(l2);
        ListNode head = new ListNode(-1);
        int carry = 0;
        while (!l1Stack.isEmpty() || !l2Stack.isEmpty() || carry > 0) {
            int x = l1Stack.isEmpty() ? 0 : l1Stack.pop();
            int y = l2Stack.isEmpty() ? 0 : l2Stack.pop();
            int sum = x + y + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            
            // 把node插入到头部
            node.next = head.next;
            head.next = node;
        }
        return head.next;
    }
    
    public Stack<Integer> buildStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        return stack;
    }
}