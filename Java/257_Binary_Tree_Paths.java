
// 解法1：递归（非最优，但是好理解）
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<String>();
        if (root != null)
            buildPath(root, "", res);
        return res;
    }
    
    private void buildPath(TreeNode root, String curPath, List<String> res){
        // 如果当前是根节点，把本节点添加到现path后，把path添加到answer列表中
        if (root.left == null && root.right == null)
            res.add(curPath + root.val);

        // 如果左子节点非空，把本节点添加到现path后（放一个“->”），递归构建当前path
        if (root.left != null)
            buildPath(root.left, curPath + root.val + "->", res);

        if (root.right != null)
            buildPath(root.right, curPath + root.val + "->", res);
    }
}
// Runtime: 8 ms, faster than 57.27% of Java


// 解法2：递归，用StringBuilder（最优）
// Timt: O(N)
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(root, sb, res);
        return res;
    }
    
    private void helper(TreeNode root, StringBuilder sb, List<String> res){
        if (root == null) return;
        int len = sb.length();
        // 先把当前node放入current path
        sb.append(root.val);
        // 如果当前为根节点，本path结束，直接把本path添加入result
        if (root.left == null && root.right == null)
            res.add(sb.toString());
        // 如果不是根节点，先添加一个->到path，然后递归构建
        else{
            sb.append("->");
            helper(root.left, sb, res);
            helper(root.right, sb, res);
        }
        // backtrack
        sb.setLength(len);
    }
}
// Runtime: 1 ms, faster than 99.81% of Java 
