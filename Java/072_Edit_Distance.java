// DP
// 相等则不需要操作，否则取删除、插入、替换最小操作次数的值+1
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        // 如果其中一个是空串
        if (n1 * n2 == 0) return n1 + n2; 
        int[][] dp = new int[n1 + 1][n2 + 1];
        
        // 边界初始化
        for (int i = 1; i <= n1; i++)
            dp[i][0] = i;   // 空串与word1前i个的距离
        for (int j = 1; j <= n2; j++)
            dp[0][j] = j;   // 空串与word2前j个的距离
    
        for (int i = 1; i <= n1; i++){
            for (int j = 1; j <= n2; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    // 本来就相等，不需要任何操作
                    dp[i][j] = dp[i - 1][j - 1];
                else// 否则取删除、插入、替换最小操作次数的值+1
                    dp[i][j] = 1 + Math.min(
                                dp[i - 1][j - 1],       // 替换
                                Math.min(dp[i - 1][j],  // 删除 
                                         dp[i][j - 1]));// 插入
            }
        }
        return dp[n1][n2];
    }
}