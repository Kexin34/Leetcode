
// Solution: Recursion
// Time complexity: O(n1 + n2)
// Space complexity: O(n1 + n2)

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafs1 = new ArrayList<>();
        List<Integer> leafs2 = new ArrayList<>();
        getLeafs(root1, leafs1);
        getLeafs(root2, leafs2);
        // 开始对比数列
        if (leafs1.size() != leafs2.size()) return false;
        for (int i = 0; i < leafs1.size(); i++){
            if (leafs1.get(i) != leafs2.get(i))
                return false;
        }
        return true;
    }
    // pre-Order DFS traversal
    public void getLeafs(TreeNode root, List<Integer> leafs){
        if (root == null) return;
        if (root.left == null && root.right == null)
            leafs.add(root.val);
        getLeafs(root.left, leafs);
        getLeafs(root.right, leafs);
    }
}
// Runtime: 0 ms, faster than 100.00% of Java