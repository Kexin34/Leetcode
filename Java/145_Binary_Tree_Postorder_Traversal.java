// 递归 (简单)
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        if (root == null) return result;
        DFS(root, result);
        return result;
    }
    
    public void DFS(TreeNode node, List<Integer> result){
        if (node == null) return;
        DFS(node.left, result);
        DFS(node.right, result);
        result.add(node.val);
    }
}
// faster than 100.00% of Java
// Time complexity: O(n)
// Space complexity: O(n)

// 遍历
/* 左右中 = 中右左+翻转 = 前序遍历（先访问右再左）+翻转 = 遍历（先压左子树再压右边）+ 翻转
*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node == null) continue;
            result.add(node.val);
            stack.push(node.left);
            stack.push(node.right);
        }
        // 翻转链表即为答案
        Collections.reverse(result);
        return result;
    }
}
// Time complexity: O(n)
// Space complexity: O(n)