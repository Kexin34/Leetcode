// 递归 (相对简单)
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        DFS(root, result);
        return result;
    }
    
    public void DFS(TreeNode node, List<Integer> result){
        if (node == null) return;
        DFS(node.left, result);
        result.add(node.val);
        DFS(node.right, result);
    }
}
//Time complexity : O(n). 
//because the recursive function is T(n) = 2*T(n/2)+1T(n)=2⋅T(n/2)+1.
// Space complexity : The worst case space required is O(n)O(n)
// the average case it's O(\log n)O(logn) where nn is number of nodes.


// 遍历
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        
        while (!stack.isEmpty() || cur != null){
            // 一口气把最左边所有都压入
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            ans.add(node.val);
            cur = node.right;
        }
        return ans;
    }
}
// Time complexity : O(n).
// Space complexity : O(n).

