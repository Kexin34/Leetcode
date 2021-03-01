// 解法：递归
// Time complexity: O(n)
// Space complexity: O(h)

class Solution {
    int ans = 0;
    
    public int sumNumbers(TreeNode root) {
        traverse(root, 0);
        return ans;
    }
    
    public void traverse(TreeNode root, int num){
        if (root == null) return;
        num = num * 10 + root.val;
        
        // if current node is leaf, add the num into answer
        if (root.left == null && root.right == null)
            ans += num;
        else{
            traverse(root.left, num);
            traverse(root.right, num);
        }
    }
}
// Runtime: 0 ms, faster than 100.00% of Java