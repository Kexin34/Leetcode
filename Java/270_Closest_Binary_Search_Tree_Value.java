// ## Basic Ideas:
// ##    If the exact match, return
// ##    If current node is bigger than the target, we get a starting value.
// ##        Then check left sub-tree
// ##    If smaller, get a starting value. Then check right sub-tree
// ##
// ## Complexity: Time O(log(n)), Space O(1)

class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (root.val - target == 0) return root.val;
            if (Math.abs(res - target) > Math.abs(target - root.val)) 
                res = root.val;
            if (target > root.val)
                root = root.right;
            else
                root = root.left;
        }
        return res;
    }
}
// 0 ms, faster than 100.00% of Java