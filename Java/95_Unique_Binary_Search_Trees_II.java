//递归解法
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return new LinkedList<TreeNode>();
        return generateSubtrees(1, n);
    }
    
    private List<TreeNode> generateSubtrees(int start, int end){
        LinkedList<TreeNode> ans = new LinkedList<TreeNode>();
        
        /* 此时没有数字，将 null 加入结果中 */
        if(start > end){        // based case: conquer
            ans.add(null);
            return ans;
        }
        /* 尝试每个数字作为根节点 */
        for (int i = start; i <= end; i++){ 
            //得到所有可能的左子树
            List<TreeNode> leftSubtrees = generateSubtrees(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightSubtrees = generateSubtrees (i + 1, end);
            
            //i的左子树右子树两两组合
            for (TreeNode left : leftSubtrees){
                for (TreeNode right : rightSubtrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
