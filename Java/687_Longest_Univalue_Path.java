// 解法：递归

class Solution {
    int maxCnt; 

    public int longestUnivaluePath(TreeNode root) {
        maxCnt = 0;
        arrowLength(root);
        return maxCnt;
    }
    
    // arrowLength:当前节点返回给父节点的值就是:从当前节点出发，向下延伸与其值相同的最大深度
    public int arrowLength(TreeNode root) {
        if (root == null) return 0;
        int left = arrowLength(root.left);
        int right = arrowLength(root.right);
        // 1.if( 如果当前节点与其左右节点都不相等)，那么深度为0，则返回0 
        // 2. else，这个最大深度maxCnt就取其左右子树返回值中的较大者 + 1
        if (root.left != null && root.left.val == root.val)
            left += 1;
        else left = 0;
        
        if (root.right != null && root.right.val == root.val)
            right += 1;
        else right = 0;
        // 左右子树返回的值相加 就是贯穿自己的最长路径了
        maxCnt = Math.max(maxCnt, left + right);
        return Math.max(left, right);
    }
}
//  faster than 94.38% of Java



// 同一个解法，整理一下
class Solution {
    private int maxCnt = 0; 
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxCnt;
    }
    
    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        int leftPath = (root.left != null && root.left.val == root.val) ? left + 1 : 0;
        int rightPath = (root.right != null && root.right.val == root.val) ? right + 1 : 0;

        maxCnt = Math.max(maxCnt, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
//  faster than 94.38% of Java