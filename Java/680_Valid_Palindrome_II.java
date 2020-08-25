class Solution {
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right))
                // 删除左边的字符，还是右边的字符, 两种情况都要算一遍
                // 发现第一个不同时，就进入子函数，接着查找是否有第二个不同
                // 只要有一种能返回true，那么结果就返回true
                return isValid(s, left, right - 1) || isValid(s, left + 1, right);
            ++left; 
            --right;
        }
        return true;
    }
    
    // 子函数来判断字符串中的某一个范围内的子字符串是否为回文串
    public boolean isValid(String s, int left, int right){
        while (left < right){
            if (s.charAt(left) != s.charAt(right))
                return false;
            ++left; 
            --right;
        }
        return true;
    }
}
// faster than 78.21% of Java