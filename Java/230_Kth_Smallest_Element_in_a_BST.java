// 我的中序遍历解法（建立整个有序array再直接按照index找出答案，适合：稳定不变的树）
class Solution {
    ArrayList<Integer> arr;
    
    public int kthSmallest(TreeNode root, int k) {
        arr = new ArrayList<>();
        inOrder(root);
        return arr.get(k - 1);
    }
    public void inOrder(TreeNode root){
        if (root == null) return;
        inOrder(root.left);
        arr.add(root.val);
        inOrder(root.right);
    }
}
// faster than 100.00% of Java 



// 中序遍历解法(适合：k比较小的时候，可以提早结束，也适合不稳定（随时插入删除的树）
class Solution {
    int res = 0;
    int cnt = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        inOrder(root, k);
        return res;
    }
    //标准中序遍历
    //count：当前节点在数组中的位置，因为已经遍历完所有左侧count个节点 
    //count == k表示当前遍历的节点是第k小节点
    private void inOrder(TreeNode root, int k){
        if (root == null) return;
        inOrder(root.left, k);
        
        cnt++;
        if (cnt == k){
            res = root.val;
            return;
        }
            
        inOrder(root.right, k);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java 




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