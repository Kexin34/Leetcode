// 解法一：直接用字符串解题

class Solution {
    public int longestPalindrome(String s) {
        int[] cnts = new int[256];
        //统计每个字符出现的个数
        for(char c : s.toCharArray())
            cnts[c]++;

        int palindrome = 0;
        for(int cnt : cnts)
            palindrome += (cnt / 2) * 2;//每个字符有偶数个可以用来构成回文字符串。

        if(palindrome < s.length())
            // 这个条件下 s 中一定有单个未使用的字符存在，可以把这个字符放到回文的最中间
            palindrome++;

        return palindrome;
    }
}
// faster than 100.00% of Java 

