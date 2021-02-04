

class Solution {
    public String breakPalindrome(String palindrome) {
        char[] s = palindrome.toCharArray();
        int n = s.length;
        
        // If only one character, return empty string.
        if (n == 1) return "";
        
        // For the first half of the string, replace the first non ‘a’ character to ‘a’.
        for (int i = 0; i < n / 2; i++){
            if (s[i] != 'a'){
                s[i] = 'a';
                return String.valueOf(s);
            }
        }
        //if all 'a', repalce the last character to 'b'
        s[n - 1] = 'b';
        return String.valueOf(s);
        
    }
}
// faster than 100.00% of Java
// Time O(N), Space O(N)


