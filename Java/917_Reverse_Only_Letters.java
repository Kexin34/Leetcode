// 双指针

class Solution {
    public String reverseOnlyLetters(String S) {
        int i = 0, j = S.length() - 1;
        char[] res = new char[S.length()];
        while (i <= j) {
            char c1 = S.charAt(i);
            char c2 = S.charAt(j);
            if (Character.isLetter(c1) && Character.isLetter(c2)){
                res[i++] = c2;
                res[j--] = c1;
            }else if (!Character.isLetter(c1))
                res[i++] = c1;
            else if (!Character.isLetter(c2))
                res[j--] = c2;
                
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : res)
            sb.append(ch);
        return sb.toString();
    }
}
// Runtime: 0 ms, faster than 100.00% of Java