// 解法：DP （基础思路，算是暴力）
class Solution {
    public int jump(int[] nums) {
    // 状态：f[i] 表示从起点到当前位置最小次数
    // 推导：f[i] = f[j], a[j]>=i-j, min(f[j]+1)
    // 初始化：f[0] = 0
    // 结果：f[n-1]
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++){
            dp[i] = i;      			// 随意让它初始化为一个较大数
            for (int j = 0; j < i; j++){
                if (nums[j] >= i - j)   // 如果j位可前进步数>= i和j的距离差，合法
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[n - 1];
    }
}
// 时间TLE




// 动态规划+贪心优化
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int steps = 0;
        int i = 0;
        int cur = 0;            // 当前的能到达的最远位置
        
        while (cur < n - 1){    // 未到达末尾index时继续循环
            steps++;
            int pre = cur;      // 表示上一次循环后能到达的最远位置
            //当前位置i <= pre，说明还是在上一跳能到达的范围内
            for (; i <= pre; i++)
                cur = Math.max(cur, i + nums[i]);

            //if (cur == pre) return -1;    // May not need this
        }
        return steps;
    }
}
// faster than 38.99% of Java 






