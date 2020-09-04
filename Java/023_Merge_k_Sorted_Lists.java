// 这道题最重要是用 “优先队列” 来解，未看
// https://leetcode.wang/leetCode-23-Merge-k-Sorted-Lists.html


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
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //省略
    }
}
//faster than 81.64% of Java



// 解法三：优先队列
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
            cur.next = pq.poll();
            cur = cur.next;
            //判断当前poll出的链表是否为空，不为空就重新将其再次入队
            ListNode next = cur.next;
            if (next != null) pq.add(next);
        }
        return dummy.next;
    }
}



