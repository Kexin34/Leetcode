// 这道题最重要是用 “优先队列” 来解，未看
// https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html


// Merge Sort （好理解）
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        return merge(lists, 0, lists.length - 1);
    }
    
    public ListNode merge(ListNode[] lists, int l, int r){
        // 3个base cases
        if (l > r) return null;    // 空
        if (l == r) return lists[l];    //只有1个，直接返回
        if (l + 1 == r) // 如果只剩下两个list，直接merge
            return mergeTwoLists(lists[l], lists[r]);
        
        int mid = l + (r - l) / 2;
        ListNode l1 = merge(lists, l, mid);
        ListNode l2 = merge(lists, mid + 1, r);
        return mergeTwoLists(l1, l2);
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(-1);// 哨兵节点
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
            }else{
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        // 有可能其中一个还有node，判断
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
//Runtime: 1 ms, faster than 100.00% of Java





// Method 1: 两两合并
// 第 0 个和第 1 个链表合并，新生成的再和第 2 个链表合并
// 新生成的再和第 3 个链表合并...直到全部合并完
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        ListNode head = mergeTwoLists(lists[0], lists[1]);
        
        for(int i = 2; i < lists.length; i++)
            head = mergeTwoLists(head, lists[i]);
        return head;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);    // 哨兵节点
        ListNode dummy = prehead;
        while(l1 != null && l2 != null){
            if(l1.val > l2.val){
                dummy.next = new ListNode(l2.val);
                l2 = l2.next;  
            }else{
                dummy.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            dummy = dummy.next;
        }
        dummy.next = l1 == null ? l2 : l1;
        return prehead.next;
    }
}
//faster than 6.07% of Jav


// Method 2: 两两合并优化
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];
        
        int interval = 1;
        while(interval < lists.length){
            // 每层两两合并
            for(int i = 0; i + interval < lists.length; i += interval * 2)
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            interval *= 2;
        }
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
// Runtime: 1 ms, faster than 100.00% of Java




// 解法三：优先队列
// Time complexity: O(nklogk)
// Space complexity: O(k)

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        
        //定义优先队列的比较器,优先级定义为数越小优先级越高
        Comparator<ListNode> cmp;
        cmp = new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2){
                return n1.val - n2.val;
            }
        };
        
        //建立优先队列，按照链表头节点大小排入pq
        Queue<ListNode> pq = new PriorityQueue<ListNode>(cmp);
        for (ListNode l : lists)
            if (l != null) pq.add(l);
        
        // 建立result链表头
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        while (!pq.isEmpty()){
            //出队列
            ListNode smallHead = pq.poll();
            cur.next = smallHead;
            cur = cur.next;
            //判断当前poll出的链表是否为空，不为空就重新将其再次入队
            if (smallHead.next != null)
                pq.add(smallHead.next);
        }
        return dummy.next;
    }
}
// Runtime: 7 ms, faster than 24.63% of Java


