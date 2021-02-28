// in-order recurstion + 取中点递归构建左右子树

class Solution {
    List<TreeNode> arr = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return build(0, arr.size() - 1);
    }
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        arr.add(root);
        inOrder(root.right);
    }
    public TreeNode build(int l, int r){
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode root = arr.get(m);
        root.left = build(l, m - 1);
        root.right = build(m + 1, r);
        return root;
    }
}
// Runtime: 2 ms, faster than 99.87% of Java 