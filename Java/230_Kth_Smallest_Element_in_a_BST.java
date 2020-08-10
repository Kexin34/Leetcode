// 中序遍历解法

class Solution {
    private int count;
    private int val;
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        inOrder(root, k);
        return val;
    }
    //标准中序遍历
    //count：当前节点在数组中的位置，因为已经遍历完所有左侧count个节点 
    //count == k表示当前遍历的节点是第k小节点
    public void inOrder(TreeNode node, int k){
        if (node == null) return;
        // 左
        inOrder(node.left, k);
        // 中
        count++;
        if (k == count){
            val = node.val;
            return;
        }
        // 右
        inOrder(node.right, k);
    }
}
//faster than 54.65% of Java

// DFS递归解法
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int leftCount = count(root.left);//算出左子树所有node数目，因为BST从小到大
        if (leftCount == k - 1) return root.val;// (k-1)th element就是第kth小元素
        if (leftCount > k - 1)  // 还没到k-1，继续搜索左子树
            return kthSmallest(root.left, k);
        else                                    //超过了左子树，(k-1)th在右子树，接着搜索
            return kthSmallest(root.right, k - 1 - leftCount);
    }
    
    // Return the total sub nodes under this node
    public int count(TreeNode node){
        if (node == null) return 0;
        return count(node.left) + count(node.right) + 1;
    }
}
// faster than 100.00% of Java