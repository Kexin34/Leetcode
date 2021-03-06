// 解法一：递归（好理解，非最优）
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return count(root, sum) 
            + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int count(TreeNode root, int sum){
        if (root == null) return 0;
        return (root.val == sum ? 1 : 0) 
            + count(root.left, sum - root.val) + count(root.right, sum - root.val);
    }
}
// faster than 35.21% of Java
// Time complexity: O(n^2)
// Space complexity: O(n)

//同样解法，注释版
class Solution {
    //PathSum 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中，和为目标值的路径总数。
    public int pathSum(TreeNode root, int sum) {
       if(root == null) return 0;
        int pathImLeading = count(root, sum); // 自己为开头的路径数
        int leftPathSum = pathSum(root.left, sum);// 左边路径总数（相信他能算出来）
        int rightPathSum = pathSum(root.right, sum); // 右边路径总数
        return leftPathSum + rightPathSum + pathImLeading;
    }
    
    // count 函数：给他一个节点和一个目标值，他返回以这个节点为根的树中,
    // 能凑出几个以该节点为路径开头，和为目标值的路径总数。
    int count(TreeNode node, int sum){
        if(node == null) return 0;
        // 我自己能不能独当一面，作为一条单独的路径呢？
        int isMe = (node.val == sum) ? 1 : 0;
        // 左子树能凑几个 sum - node.val？
        int leftBrother = count(node.left, sum - node.val);
        // 右子树能凑几个 sum - node.val？
        int rightBrother = count(node.right, sum - node.val);
        return isMe + leftBrother + rightBrother;// 我这能凑这么多个
    }
}
//faster than 56.42% of Java 




// 解法二： 前缀和，递归（最优解法）
// Time complexity: O(n)
// Space complexity: O(h)
class Solution {
    int count = 0;
    HashMap<Integer, Integer> preSum = new HashMap<>();
    
    public int pathSum(TreeNode root, int sum) {
        preSum.put(0, 1); // sum = 0的次数是1
        helper(root, 0, sum);
        return count;
    }
    private void helper(TreeNode root, int curSum, int sum){
        if (root == null) return;

        curSum += root.val;  // cur_sum = sum(nums[0:i])
        // 重点：check how many arrays nums[0:j] (j < i) that has sum (cur_sum – k)
        // then there are the same number of arrays nums[j+1: i] that have sum k.
        count += preSum.getOrDefault(curSum - sum, 0);// 
        preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1); // 更新prefixSum[i时候sum]的个数

        // 左右递归计算
        helper(root.left, curSum, sum);
        helper(root.right, curSum, sum);

        // backtrack
        preSum.put(curSum, preSum.get(curSum) - 1);
    }
}
// Runtime: 3 ms, faster than 61.09% of Java



// 注释版
class Solution {
    // map：前缀和 -> 该前缀和出现的次数
    HashMap<Integer, Integer> preSum = new HashMap<>();
    int count = 0;
    int target;
    
    public int pathSum(TreeNode root, int sum) {
        target = sum;
        preOrder(root, 0);
        return count;
    }
    
    public void preOrder(TreeNode root, int curr_sum) {
        if (root == null) return;
        //计算最新前缀和
        curr_sum += root.val;
        // 如果当前前缀和等于目标，立刻更新count
        if (curr_sum == target)
            count++;
        
        // 这是我们想找的前缀和 nums[0..j]
        // 如果前面有这个前缀和，则直接更新答案
        count += preSum.getOrDefault(curr_sum - target, 0);
        
        // 把前缀和 nums[0..i] 加入并记录出现次数
        preSum.put(curr_sum, preSum.getOrDefault(curr_sum, 0) + 1);
        
        // 前序遍历，继续递归左右子树
        preOrder(root.left, curr_sum);
        preOrder(root.right, curr_sum);
        
        // 回溯结束 resume
        // remove the current sum from the hashmap
        // In order not to use it during the parallel subtree processing
        preSum.put(curr_sum, preSum.get(curr_sum) - 1);
    }
}
//  faster than 100.00% of Java
// Time complexity: O(N), where N is a number of nodes. 
// During preorder traversal, each node is visited once.
// Space complexity: up to O(N) to keep the hashmap of prefix sums