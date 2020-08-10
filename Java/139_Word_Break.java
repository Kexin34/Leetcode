// 完全背包问题 - DP
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++){   // 对背包的迭代应放在外层
            for (String word : wordDict){   // 对物品的迭代应放在里层
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i)))
                    // 因为要连续符合才行，要看i-len位置是否也符合
                    dp[i] = dp[i] || dp[i - len];
            }
        }
        return dp[n];
    }
}
// faster than 86.75% of Java