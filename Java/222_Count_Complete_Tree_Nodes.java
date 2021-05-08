// 最优解：
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        // 记录左、右子树的高度
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        
        while (l != null) {
            hl++;
            l = l.left;
        }
        while (r != null) {
            hr++;
            r = r.right;
        }
        // 如果左右子树的高度相同，则是一棵满二叉树
        if (hl == hr)
            return (int)Math.pow(2, hr) - 1;
        // 如果左右高度不同，则按照普通二叉树的逻辑计算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// O(logN*logN), 递归深度就是树的高度 O(logN)，每次递归所花费的时间就是 while 循环，需要 O(logN)，
// 所以总体的时间复杂度是 O(logN*logN)






// 解法一：递归 （最简单直接）
// 本解法没有利用原题是”完整“二叉树，普通无序二叉树也可用一下方法

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        else
            return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
// O(N)，faster than 100.00% of Java


// 重点解法二：binary search （利用了”完整“二叉树特性）
// https://leetcode.com/articles/count-complete-tree-nodes/
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int d = computeDepth(root);
        if (d == 0) return 1;// if the tree contains 1 node
        
        /* Last level nodes are enumerated from 0 to 2^d - 1 (left -> right).
         * Perform binary search to check how many nodes exist. 
         * 二分查找缺失的那个node，找到可以停下了,left指针记录着停下来的地方（个数）
         */
        int left = 1, right = (int) Math.pow(2, d) - 1;
        int pivot;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (exists(pivot, d, root)) 
                left = pivot + 1;
            else 
                right = pivot - 1;
        }
        // The tree contains 2^d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (int)Math.pow(2, d) - 1 + left;
    }
    
    /* Return tree depth in O(d) time. */
    public int computeDepth(TreeNode node){
        int d = 0;
        while (node.left != null){
            node = node.left;
            ++d;
        }
        return d;
    }
    
    /* Last level nodes are enumerated from 0 to 2^d - 1 (left -> right).
     * Return True if last level node idx exists. 
     * Binary search with O(d) complexity.
    */
    public boolean exists(int idx, int d, TreeNode node){
        int left = 0, right = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; ++i){
            pivot = left + (right - left) / 2; //mid
            if(idx <= pivot){
                node = node.left;
                right = pivot;
            }else{
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }
}
// faster than 100.00% of Java
