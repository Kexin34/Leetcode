// 快慢指针 + 递归
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return helper(head, null);
    }
    
    // 两个输入参数，子链表的起点和终点, 直接将中间部分转换为二叉搜索树
    public TreeNode helper(ListNode head, ListNode tail){
        if (head == tail) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        // 找中点
        while (fast != tail && fast.next != tail){//这么写因为有奇偶两种长度
            fast = fast.next.next;
            slow = slow.next;
        }
        // 这时候，slow指向的就是中点，即本range的root node
        TreeNode node = new TreeNode(slow.val);
        node.left = helper(head, slow);
        node.right = helper(slow.next, tail);
        return node;
    }
}
// faster than 100.00% of Java
//Time: N * 2 log(N) so it's O(n log n)
