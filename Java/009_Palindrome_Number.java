// Source : https://leetcode.com/problems/palindrome-number/
// Author : Kexin Wen
// Date   : 2020-05-19

class Solution {
    public boolean isPalindrome(int x) {
        if(x == 0)
            return true;

        // The last number of palindrome cannot be 0
        if(x < 0 || x % 10 == 0){
            return false;
        }
        // 将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
        int right = 0;
        while(x > right){
            right = right * 10 + x  % 10; //reverse，把新数字放入最右（最低位）
            x /= 10;
        }
        return x == right || x == right / 10;
    }
}