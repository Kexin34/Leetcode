class Solution {
    public boolean isPalindrome(String s) {
        int len = s.length();
        for(int i = 0, j = len - 1; i < j; i++, j--){
            while(i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while(i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
            if(i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }
        return true;
    }
}
// faster than 97.87% of Java 
// 时间复杂度为O(n)



// 同样解法，如果面试官不让系统自带的判断函数，自己写函数
class Solution {
    public boolean isPalindrome(String s) {
        int len = s.length();
        for(int i = 0, j = len - 1; i < j; i++, j--){
            // 左右都skip掉所有非字母/数字字符
            while (i < j && !isAlphaNum(s.charAt(i)))
                i++;
            while (i < j && !isAlphaNum(s.charAt(j)))
                j--;
            // convert to lower case by adding 32,  'a' - 'A' = 32
            // -'a' to get the value, % 32 to ??????
            if (i < j && (((s.charAt(i) + 32 - 'a')) % 32 != ((s.charAt(j) + 32 - 'a')) %32))
                return false;
        }
        return true;
    }
    
    public boolean isAlphaNum(char ch){
        if (ch >= 'a' && ch <= 'z') return true;
        if (ch >= 'A' && ch <= 'Z') return true;
        if (ch >= '0' && ch <= '9') return true;
        return false;
    }
}
// faster than 97.87% of Java 