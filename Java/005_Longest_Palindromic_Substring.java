// Method 1: 双指针解法
class Solution {
    private int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        
        for(int i = 0; i < len - 1; i++){
            palindrome(s, i, i);// 以 s[i] 为中心的最长回文子串
            palindrome(s, i, i + 1);// 以 s[i] 和 s[i+1] 为中心的最长回文子串
        }
        return s.substring(lo, lo + maxLen);
    }
   
    
    private void palindrome(String s, int l, int r){
        // 防止索引越界
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            // 向两边展开
            l--; r++;
        }
        // 返回以 s[l] 和 s[r] 为中心的最长回文串
        //return s.substring(l + 1, r - l - 1);//+1-1为了排除最后不等的元素
        if (maxLen < r - l - 1) {
		    lo = l + 1;
		    maxLen = r - l - 1;
	    }
    }
}
// faster than 91.17% of Java，时间复杂度 O(N^2)，空间复杂度 O(1)。


// method 2：动态规划 Dynamic Programming
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || "".equals(s))
            return s;
        
        int len = s.length();
        String ans = "";
        int max = 0;
        
        boolean[][] dp = new boolean[len][len];
        
        for (int j = 0; j < len; j++){
            for (int i = 0; i <= j; i++){
                boolean judge = (s.charAt(i) == s.charAt(j));

                if (j - i > 2)
                    dp[i][j] = dp[i + 1][j - 1] && judge;
                else
                    dp[i][j] = judge;
                
                if(dp[i][j] && (j - i + 1) > max){
                    max = j - i + 1;
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
// faster than 20.34% of Java 