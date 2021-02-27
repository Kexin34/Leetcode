// 解法1：递归解法
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return new LinkedList<TreeNode>();
        return generateSubtrees(1, n);
    }
    
    private List<TreeNode> generateSubtrees(int start, int end){
        LinkedList<TreeNode> ans = new LinkedList<TreeNode>();
        /* based case：此时没有数字，将 null 加入结果中 */
        // 递归结束，把本树贴上空节点，然后返回本树
        if(start > end){     
            ans.add(null);
            return ans;
        }
        /* 尝试每个数字作为根节点 */
        // 遍历当前左右，每个节点都当做是根节点，并递归左右子树并贴上当前父节点
        for (int i = start; i <= end; i++){ 
            //递归生成所有可能的左右子树
            List<TreeNode> leftSubtrees = generateSubtrees(start, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees (i + 1, end);
            
            //i的左子树右子树两两组合
            for (TreeNode left : leftSubtrees){
                for (TreeNode right : rightSubtrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);//加入到最终结果中
                }
            }
        }
        return ans;
    }
}

// Time complexity: O(3^n)
// Space complexity: O(3^n)