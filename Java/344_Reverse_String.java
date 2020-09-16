// 我自己写的，可以当成暴力解
class Solution {
    public void reverseString(char[] s) {
        if (s.length == 0 || s.length == 1) return;
        int n = s.length;
        for (int i = 0; i < n / 2; i++){
            char tmp = s[i];
            s[i] = s[n - 1- i];
            s[n - 1 - i] = tmp;
        }
    }
}
// faster than 19.70% of Java
// Time : O(N) to swap N/2 element.
// Space : O(1), it's a constant space solution.


// 解法二：递归
class Solution {
    public void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }
    
    private void reverse(char[] s, int left, int right){
        if (left >= right) return;
        
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        
        reverse(s, left, right);
    }
}
// faster than 67.85% of Java 
// Time complexity :O(N) time to perform N/2N/2 swaps.
// Space complexity : O(N) to keep the recursion stack.