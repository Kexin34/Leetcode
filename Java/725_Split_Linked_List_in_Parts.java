
// 解法一：
// 先遍历一次知道长度，len/k = 答案里每个[]元素个数base，len % k = 前面几个[]要多放入的个数
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode cur = root;
        int len = 0;
        while (cur != null){
            len++;
            cur = cur.next;
        }
        int width = len / k;
        int remain = len % k;
        
        // 创建一个新列表，并将该部分写入该列表对应部分
        ListNode[] res = new ListNode[k];
        cur = root;

        // 遍历循环每个[]，建立每个子链表
        for (int i = 0; cur != null && i < k ; i++){
            res[i] = cur;
            // 分隔的每个部分都有len/k个节点，且前len/k部分还有一个额外的节点
            int curSize = width + (remain-- > 0 ? 1 : 0);

            for (int j = 0; j < curSize - 1; j++)
                cur = cur.next;

            // 存下一个节点，因为要把当前子链表断开
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return res;
    }
}
// faster than 100.00% of Java 
// Time: O(N + K)
// Space: O(max(N, k))