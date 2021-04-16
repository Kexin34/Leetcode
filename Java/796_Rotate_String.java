// A="abcde", B="bcdea"，那么A+A="abcdeabcde"

class Solution {
    public boolean rotateString(String A, String B) {
       return A.length() == B.length() && (A + A).contains(B);
    }
}
// 0 ms, faster than 100.00% of Java 


// 这道题应该用KMP (Knuth-Morris-Pratt)做