// Time: O(n + gcd(n,m) *m), where n is length of string s, and m is length of string t.

class Solution {
    public String gcdOfStrings(String s, String t) {
        int l1 = s.length(), l2 = t.length();
        int i1 = 0, i2 = 0;
        int end = gcd(l1, l2), start = 1;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c1 = s.charAt(i1++), c2 = t.charAt(i2++);
            if (c1 != c2) return "";
            sb.append(c1);
            start++;
        }
        String gcdString = sb.toString();
        return isDivisible(s, gcdString) && isDivisible(t, gcdString) ? gcdString : "";
    }
    
    private static int gcd(int a, int b) {
//        a should be greater then b.
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static boolean isDivisible(String s, String t) {
        StringBuilder sb = new StringBuilder(t);
        while (sb.length() <= s.length()) {
            if (sb.toString().equals(s)) return true;
            sb.append(t);
        }
        return false;
    }
}