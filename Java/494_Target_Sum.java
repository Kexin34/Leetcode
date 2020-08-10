// 解法：DP 背包问题解法(常规)
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (S > 1000) return 0;
        int n = nums.length;
        // dp[i][j]: 数组中前i个元素，组成和为j的方案数
        int[][] dp = new int[n][2001];// sum range: -1000~+1000
        
        // base cases
        dp[0][nums[0] + 1000] = 1;//第0个，组成和 = 第一个nums数只能让容积为它自己的背包恰好装满
        dp[0][-nums[0] + 1000] += 1; //添加负号，也只有一个
        
        for (int i = 1; i < n; i++){
            for (int sum = -1000; sum <= 1000; sum++){
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];//被添加+ 
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];// 被添加-
                }
            }
        }
        return dp[n - 1][S + 1000];
    }
}
// faster than 78.66% of Java



// 解法DP（优化）背包另一种解, 从后往前，不需要二维DP数组
// 找到一个子集，令他们都取正号，并且和等于（S + sum（nums））/ 2证明存在解
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < S || (sum + S) % 2 == 1) return 0;
        int target = (sum + S) / 2;
         
        // dp[i]: target为i的时候有多少种组合
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums){
            for (int i = target; i >= num; i--)//从后往前，先计算dp[i]再计算dp[i - num]
                dp[i] = dp[i] + dp[i - num];
        }
        return dp[target];
    }
}
// faster than 100.00% of Java





// 解法：递归（非优化）
class Solution {
    int res;
    public int findTargetSumWays(int[] nums, int S) {
        res = 0;
        helper(nums, S, 0);
        return res;
    }
    
    public void helper(int[] nums, int S, int start){
        // 递归结束case
        if (start >= nums.length){
            if (S == 0) res++;  //  如果结束时sum等于S，有一个答案
            return;
        }
        // 分别对目标值进行加上当前数字调用递归，和减去当前数字调用递归
        helper(nums, S - nums[start], start + 1);
        helper(nums, S + nums[start], start + 1);
    }
}
// faster than 33.81% of Java



// 解法：记忆性递归（优化，memo）
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        // 因为要用memo[][]，而S有可能是负数，所以需要平移一下目标S
        // 目标sum和起始sum都是从所有ele和这个数开始
        int sum = 0;
        for (int num : nums) sum += num;
        Integer[][] memo = new Integer[nums.length][2 * sum + 1];
        
        return helper(nums, sum, S + sum, 0, memo);
    }
    
    public int helper(int[] nums, int curSum, int target, int start, Integer[][] memo){
        // 递归结束case
        if (start == nums.length) 
            return curSum == target ? 1 : 0;//找到一条合法path，返回1
        
        if (memo[start][curSum] != null) 
            return memo[start][curSum];
        
        // 分别对目标值进行加上当前数字调用递归，和减去当前数字调用递归
        memo[start][curSum] = helper(nums, curSum - nums[start], target, start + 1, memo) 
                            + helper(nums, curSum + nums[start], target, start + 1, memo);
        return memo[start][curSum];
    }
}
// faster than 84.30% of Java









