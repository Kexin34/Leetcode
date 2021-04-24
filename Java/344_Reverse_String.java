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
// Runtime: 1 ms, faster than 94.74% of Java 
// Time : O(N) to swap N/2 element.
// Space : O(1), it's a constant space solution.


// 解法二：递归
class Solution {
    public void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }
    
    private void reverse(char[] s, int start, int end){
        if (start >= end) return;
        
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        start++;
        end--;
        reverse(s, start, end);
    }
}
// Runtime: 1 ms, faster than 94.74% of Java 
// Time complexity :O(N) time to perform N/2 swaps.
// Space complexity : O(N) to keep the recursion stack.