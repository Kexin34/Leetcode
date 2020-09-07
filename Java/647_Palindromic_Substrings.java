// 解法： 递归
class Solution {
    int res = 0;
    
    public int countSubstrings(String s) {
        if (s.length() == 0) return 0;
        int n = s.length();
        
        //以字符串中的每一个字符都当作回文串中间的位置,向两边扩散
        for (int i = 0; i < n; i++){
            helper(s, i, i);    //奇数长度
            helper(s, i, i+ 1); //偶数长度
        }
        return res;
    }
    
    //向两边扩散，每当成功匹配两个左右两个字符，结果 res 自增1，然后再比较下一对
    public void helper(String s, int l, int r){
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
            res++;
        }
    }
}
// faster than 97.05% of Java 


// 解法： DP
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        
        for (int i = n - 1; i >= 0; i--){
            for (int j = i; j < n; j++){
                boolean equ = s.charAt(i) == s.charAt(j);
                if (j - i > 2)
                    dp[i][j] = equ && dp[i + 1][j - 1];
                else
                    dp[i][j] = equ;
                
                if (dp[i][j]) res++;
            }
        }
        return res;
    }
}
// faster than 33.52% of Java 