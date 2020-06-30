class Solution {
    ListNode head;
    Random random;
   
    public Solution(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    /* 返回链表中一个随机节点的值 */
    public int getRandom() {
        ListNode c = head;
        int r = c.val;
        
        for(int i = 1; c.next != null; i++){ //循环遍历链表
            // 生成一个 [1, i+1) 之间的整数,这个整数等于 i 的概率就是 1/i
            c = c.next;
            if(random.nextInt(i + 1) == i) 
                r = c.val;
        }
        return r;
    }
}
 // faster than 92.64% of Java ,时间复杂度是 O(n)