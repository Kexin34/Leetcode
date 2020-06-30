// 递归解法（必须要会）
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return build(nums, 0, nums.length - 1);
    }
    
    private TreeNode build(int[] nums, int start, int end){
        if (start > end) return null;
        /* 找到当前array的最大index */
        int idxMax = start;
        for (int i = start + 1; i <= end; i++){
            if (nums[i] > nums[idxMax])
                idxMax = i;
        }
        /* 利用最大index分割，分别递归 */
        TreeNode root = new TreeNode(nums[idxMax]);
        root.left = build(nums, start, idxMax - 1);
        root.right = build(nums, idxMax + 1, end);
        
        return root;
    }
}
//faster than 89.96% of Java




//理论上（不一定要会）：The best complexity for this question O(n), using a stack
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        // 只需一次遍历原数组
        for(int i = 0; i < nums.length; i++){
            TreeNode curNode = new TreeNode(nums[i]);
            // 找到合适位置进行插入binary tree
            while(!stack.isEmpty() && stack.peek().val < nums[i])
                curNode.left = stack.pop();//最后存的是仅小于他的node
            
            if (!stack.isEmpty())
                stack.peek().right = curNode;
            stack.push(curNode);
        }
        return stack.isEmpty()? null : stack.removeLast();//占地为max node
    }
}
//faster than 21.47% of Java