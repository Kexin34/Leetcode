// 解法：按照滑窗解法写的
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray())
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        
        int left = 0, right = 0;
        int match = 0;
        while (right < s2.length()){
            // 1. 右指针右移之后窗口数据更新
            char c1 = s2.charAt(right);
            right++;
            if (needs.containsKey(c1)){
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (needs.get(c1).intValue() == window.get(c1).intValue())
                    match++;
            }
            // 当窗口长度大于字符串长度，缩紧窗口
            while (right - left >= s1.length()){
                // 当窗口长度和字符串匹配，并且里面每个字符数量也匹配时，满足条件
                if (match == needs.size())
                    return true;
                char c2 = s2.charAt(left);
                left++;
                if (needs.containsKey(c2)){
                    if (window.get(c2).intValue() == needs.get(c2).intValue())
                        match--;
                    window.put(c2, window.get(c2) - 1);
                }
                
            }
        }
        return false;
    }
}
// faster than 26.42% of Java 


// 解法：优化滑窗
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int len1 = s1.length(), len2 = s2.length();
        
        int[] count = new int[26];
        // 按照s1建立窗口
        for (int i = 0; i < len1; i++)
            count[s1.charAt(i) - 'a']++;
        
        // 这个窗在s2从头滑到尾
        for (int i = 0; i < len2; i++){
            // 当右一个字符添入窗口，map中相应count--
            count[s2.charAt(i) - 'a']--;
            // 当左一个字符离开窗口，map中相应count++
            if(i - len1 >= 0) 
                count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }
        return false;
    }
    
    private boolean allZero(int[] count){
        for (int i = 0; i < 26; i++)
            if (count[i] != 0) return false;
        return true;
    }
}
// faster than 55.09% of Java
// Time: O(l1 + 26 ∗ (l2 − l1))
// Space complexity : O(1). Constant space is used.