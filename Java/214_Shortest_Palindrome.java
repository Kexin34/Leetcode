// 解法一：双指针 + 递归

class Solution {
    public String shortestPalindrome(String s) {
        int i = 0;
        int n = s.length();
        // 从后往前,指针i和j分别指向s串的开头和末尾，
        // 若 s[i] 和 s[j] 相等，则i自增1，j自减1，否则只有j自减1
        for (int j = n - 1; j >= 0; j--){
            if (s.charAt(i) == s.charAt(j))
                i++;
        }
        if (i == n) return s;
        // [0,i) 需要再次调用递归函数来填充使其变为回文串
        // 可以确定的是后面剩余的部分肯定不属于回文前缀，此时提取出剩下的字符rem，
        // 翻转一下加到最前面，而对范围 [0, i) 内的子串再次递归调用本函数，
        String rem = s.substring(i);
        String res_rev = new StringBuilder(rem).reverse().toString();
        return res_rev + shortestPalindrome(s.substring(0, i)) + rem;
    }
}
// faster than 79.21% of Java 
