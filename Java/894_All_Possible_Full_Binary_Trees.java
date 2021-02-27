// 解法一：递归（无memo）
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        // 两个based cases：
        if (N % 2 == 0) return ans;
        if (N == 1) {
            ans.add(new TreeNode(0));
            return ans;
        }
        for (int i = 1; i < N; i+= 2) {
            // 遍历递归返回的左子树节点
            for (TreeNode left : allPossibleFBT(i)){
                // 遍历递归返回的右子树节点
                for (TreeNode right : allPossibleFBT(N - i - 1)){
                    // 构建父子关系
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}
// 2 ms, faster than 64.78% of Java


// 解法二: DP （最优解）
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        if (N % 2 == 0) return new ArrayList<>();
        List<TreeNode>[] dp = new List[N + 1];
        dp[0] = new ArrayList<TreeNode>();
        dp[0].add(null);
        dp[1] = new ArrayList<TreeNode>();
        dp[1].add(new TreeNode(0));
        
        for (int i = 3; i <= N; i+= 2) {
            dp[i] = new ArrayList<TreeNode>();
            for (int j = 1; j < i; j += 2){//左子树个数
                int k = i - j - 1;// 右子树个数
                // 把左右子树挑出来两两组合
                for (TreeNode left : dp[j]) {
                    for (TreeNode right: dp[k]){
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[N];
    }
}
// 1 ms, faster than 99.11% of Java 

