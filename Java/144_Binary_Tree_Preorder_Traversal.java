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


// 解法：DFS递归（从上往下）
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






// DFS 深度搜索-从下向上（分治法）
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new LinkedList<>();
        return divideAndConquer(root);
    }
    public List<Integer> divideAndConquer(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        if (node == null) {
            return null;
        }
        // 分治
        List<Integer> left = divideAndConquer(node.left);
        List<Integer> right = divideAndConquer(node.right);
        // 合并结果
        result.add(node.val);
        if (left != null) {
            result.addAll(left);
        }
        if (right != null) {
            result.addAll(right);
        }
        return result;
    }
}
// // 100%



// Divide & Conquer
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        
        res.add(root.val);
        res.addAll(left);
        res.addAll(right);
        return res;
    }
}
// 0 ms, faster than 100.00% of Java