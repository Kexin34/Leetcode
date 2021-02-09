
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        // corner case
        if (s == null || s.length() == 0) return true;
        
        int n = s.length();
        // pattern must repeat at least twice, i.e. pattern length is at most n/2
        for (int len = 1; len <= n / 2; len++){
            // s length must can be divided by the pattern length
            if (n % len != 0) continue;
            
            String pattern = s.substring(0, len);// pattern string
            int i = len;                 // start index of 2nd pattern
            int j = i + len - 1;         // end index of 2nd pattern
            while (j < n){
                String substr = s.substring(i, j + 1);
                // failed for this pattern, try next pattern		
                if (!pattern.equals(substr)) break;
                
                i += len;
                j += len;
            }
            // if it past the last substring check, i will be n
            if(i == n) return true;
        }
        return false;
    }
}
// faster than 86.06% of Java

