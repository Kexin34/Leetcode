// Recursion w/ extra space
class Solution {
    private int curCnt = 1;
    private int maxCnt = 1;
    private TreeNode prev = null;
    
    public int[] findMode(TreeNode root) {
        // 因为不知道答案数组大小，先用linkedlist保存答案
        
        List<Integer> maxCntNums = new ArrayList<>();// 不需要用LinkedList，这个只达到80%
        inOrder(root, maxCntNums);
        
        //把链表maxCntNums的答案转化成result数组
        int[] result = new int[maxCntNums.size()];
        int idx = 0;
        for (int num : maxCntNums)
            result[idx++] = num;
        return result;
    }  
    public void inOrder(TreeNode node, List<Integer>  maxCntNums){
        if (node == null) return;
        inOrder(node.left, maxCntNums);
        
        // 中（本题重点）
        /* 通过和上一个node对比来计算频率 */
        if (prev != null){
            if (prev.val == node.val)
                curCnt++;
            else
                curCnt = 1;
        }
        /* 更新频率最大值 */
        if (curCnt == maxCnt){          //添加
            maxCntNums.add(node.val);
        }else if (curCnt > maxCnt){     //替换掉所有
            maxCnt = curCnt;
            maxCntNums.clear();
            maxCntNums.add(node.val);
        }
        prev = node;
         
        inOrder(node.right, maxCntNums);
    }
}
//faster than 100.00% of Java
