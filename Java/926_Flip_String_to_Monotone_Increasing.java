// Prefix Sums解法
class Solution {
    public int minFlipsMonoIncr(String S) {
        int N = S.length();
        int[] P = new int[N + 1];   // P[0]作为padding
        // 填充P
        for (int i = 0; i < N; i++)
            P[i + 1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);
        
        int ans = Integer.MAX_VALUE;
        // 尝试不同的x partition，找到最小的作为答案
        for (int x = 0; x <= N; x++){
            ans = Math.min(ans, P[x] + (N - x) - (P[N] - P[x]));
        }
        return ans;
    }
}
// faster than 99.76% of Java 
// Time Complexity: O(N), where N is the length of S.
// Space Complexity: O(N).


// DP 思路解法
/* tranverse the string
 * use two variables to record the minimim number of flip need to make the 
   substring from (0 - i) to be MonoIncr with end with 1 or 0;
 * (1) for position i + 1, if preceding string is end with 1, the current string 
   can only end with one, so cntEndWithOne can only be used to update the next cntEndWithOne
 * (2) if the preceding string is end with zero, current string can either end with 
   zero or one
 * so cntEndWithZero can be used to update for both next cntEndWithOne and 
   cntEndWithZero;
 */
class Solution {
    public int minFlipsMonoIncr(String S) {
        if (S == null || S.length() <= 1) return 0;
        int n = S.length();
        int cntEndWithOne = 0, cntEndWithZero = 0;
        
        for (int i = 0; i < n; i++){    // substring from (0 - i) 
            char c = S.charAt(i);
            if (c == '1'){
                // preceding string is end with 1
                // cntEndWithOne can only be used to update the next cntEndWithOne
                cntEndWithOne = Math.min(cntEndWithZero, cntEndWithOne);
                cntEndWithZero += 1;
            }
            else{
                // preceding string is end with 0
                cntEndWithOne = Math.min(cntEndWithZero, cntEndWithOne) + 1;
                // cntEndWithZero can be used to update for both next cntEndWithOne cntEndWithZero;
            }
        }
        return Math.min(cntEndWithOne, cntEndWithZero);
    }
}
// faster than 99.76% of Java

    