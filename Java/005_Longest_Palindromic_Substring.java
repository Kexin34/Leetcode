// Method 1: 双指针解法
class Solution {
    private int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        
        // 以每一个字符为中心，像两边扩散来寻找回文串
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
            l--; 
            r++;
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
        int len = s.length();
        if (len < 2) return s;
        String ans = "";
        int maxLen = 0;
        
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++){
            for (int i = 0; i <= j; i++){
                boolean equal = (s.charAt(i) == s.charAt(j));
                
                if (j - i > 2)
                    dp[i][j] = equal && dp[i + 1][j - 1];
                else
                    dp[i][j] = equal;
                
                //如果i到j是合法串，对比是否超过maxLen,是的话更新长度&答案
                if (dp[i][j] && (j - i + 1) > maxLen){ 
                    maxLen = j - i + 1; //  index相减再+1才为长度
                    ans = s.substring(i, j + 1);//+1因为要包括j index元素
                }
            }
        }
        return ans;
    }
}
// faster than 27.45% of Java 

