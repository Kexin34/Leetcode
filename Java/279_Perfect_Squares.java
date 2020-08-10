// 最简单直接的DP解法, 记住这个！
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++){
            for (int j = 1; j * j <= i; ++j)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }
} 
// faster than 65.34% of Java
// Time complexity: O(n * sqrt(n))
// Space complexity: O(n)



// 最早看到的DP解法，基本一样，但是很啰嗦，不需要
class Solution {
    public int numSquares(int n) {
        List<Integer> squareList = generateSquaresList(n);
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for(int square : squareList){
                if(square > i){
                    break;
                }
                min = Math.min(min, dp[i - square] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
    
    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,16,25,36,49,...
     */
    private List<Integer> generateSquaresList(int n){
        List<Integer> square_list = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while(square <= n){
            square_list.add(square);
            square += diff;
            diff += 2;
        }
        return square_list;
    }
}
//  faster than 21.86% of Java 

