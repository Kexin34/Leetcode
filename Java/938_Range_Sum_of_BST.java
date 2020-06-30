// 递归 DFS
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;
        int sum = 0;
        if(root.val > L)
            sum += rangeSumBST(root.left, L, R);// left child is a possible candidate.
        if(root.val < R)
            sum += rangeSumBST(root.right, L, R);// right child is a possible candidate.
        
        if(root.val >= L && root.val <= R)
            sum += root.val;
        return sum;
    }
}
// faster than 100.00% of Java
//Time: O(n), space: O(h), where n is the number of total nodes, h is the height of the tree..


// 遍历解法 （思路和递归一样）
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        
        while(!stack.isEmpty()){
            TreeNode n = stack.pop();
            if (n == null) 
                continue;
            if (n.val > L)
                stack.push(n.left);// left child is a possible candidate.
            if (n.val < R)
                stack.push(n.right);// right child is a possible candidate.
            if (n.val <= R && n.val >= L)
                sum += n.val;
        }
        return sum;
    }
}
// faster than 22.57% of Java