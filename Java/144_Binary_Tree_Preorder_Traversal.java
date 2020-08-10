// using Stack
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node == null) continue;//之前推入的是空，跳过本轮，pop下一个node
            ans.add(node.val);
            stack.push(node.right);// 先右后左，保证左子树先遍历
            stack.push(node.left);
        }
        return ans;
    }
}
// Time complexity: O(n)
// Space complexity: O(n)

// 递归
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        DFS(root, result);
        return result;
    }
    
    public void DFS(TreeNode node, List<Integer> result){
        if (node == null) return;
        result.add(node.val);
        DFS(node.left, result);
        DFS(node.right, result);
    }
}
// 100%
// Time complexity: O(n)
// Space complexity: O(n)