// 暴力递归解法
class Solution {
    public int rob(TreeNode root) {
        if(root == null) return 0;
        int money = root.val;
        if (root.left != null)
            money += rob(root.left.left) + rob(root.left.right);
        if (root.right != null)
            money += rob(root.right.left) + rob(root.right.right);
        return Math.max(money, rob(root.left) + rob(root.right));
    }
}
//  faster than 21.01% of Java



// 解法二：记忆化递归 Recursive with Memorization
class Solution {
    Map<TreeNode, Integer> memo = new HashMap<>();
    
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (memo.containsKey(root)) //若计算过，返回该节点为止能偷的金额
            return memo.get(root);
        
        // 抢本node，然后去下下家(左子树下面的左右nodes + 右子树下面的左右nodes)
        int do_it = root.val
            + (root.left == null ? 
              0 : rob(root.left.left) + rob(root.left.right))
            + (root.right == null ?
              0 : rob(root.right.left) + rob(root.right.right));
        
        // 不抢，然后去下家(本node的左右子树nodes)
        int not_do = rob(root.left) + rob(root.right);
        
        int res = Math.max(do_it, not_do);
        memo.put(root, res);
        return res;
    }
}
// faster than 57.13% of Java
// 时间复杂度 O(N)，N 为数的节点数。




//解法三（最优）
// 优化（提升100%）代码更漂亮
class Solution {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */

    public int[] helper(TreeNode root){
        if (root == null) return new int[]{0, 0};
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        
        // 不抢，下家可抢可不抢。当前节点能偷到的最大钱数 = 左孩子能偷到的钱 + 右孩子能偷到的钱 
        int not_rob = Math.max(left[0], left[1])//左子树（抢/不抢）的最大收益
                    + Math.max(right[0], right[1]);//右子树（抢/不抢）的最大收益
        
        // 抢，下家就不能抢了。当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 
        //            右孩子选择自己不偷时能得到的钱 + 当前节点钱数
        int rob = root.val + left[0] + right[0]
        
        return new int[]{not_rob, rob};
    }
    
}
//  faster than 100.00% java
// 时间复杂度 O(N)，空间复杂度只有递归函数堆栈所需的空间，不需要备忘录的额外空间。


