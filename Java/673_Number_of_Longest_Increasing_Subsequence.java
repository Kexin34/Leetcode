// LIS DP
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];// 通用的找LIS
        int[] count = new int[n];
        Arrays.fill(count, 1);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]){//没用max，这说明找到更长的
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    }else if (dp[j] + 1 == dp[i]) {// 找到同样长度的，数量增加
                        count[i] += count[j];
                    }
                }  
            }
        }
        // 找出最大长度
        int maxLen = 0;
        for (int len: dp)
            maxLen = Math.max(maxLen, len);
        
        // 找出符合这个最大长度的LIS数量
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen)
                res += count[i];
        }
        return res;
    }
}
// Runtime: 19 ms, faster than 53.69% of Java
// time：O(n^2)

