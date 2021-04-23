// 本题是“向前型指针”题目，可以用【窗口类指针移动模板】来解

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0; 
        int j = 0;
        int max = 0;
        int[] map = new int[256];
        
        
        for (i = 0; i < s.length(); i++){
            while (j < s.length() && map[s.charAt(j)] == 0){
                map[s.charAt(j)]++;
                j++;
            }
            max = Math.max(max, j - i);// j 是第一个出界的，所以len不需要加一
            map[s.charAt(i)]--;
        }
        return max;
    }
}
// Runtime: 2 ms, faster than 99.80% of Java
// Time: O(n)






// 解法一：基础滑窗
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;    // 记录最长长度
        int left = 0, right = 0;
        
        Map<Character, Integer> window = new HashMap<>();
        
        while (right < n){
            char c1 = s.charAt(right);
            window.put(c1, window.getOrDefault(c1, 0) + 1);
            right++;
            
            //当 window 中出现重复字符时，开始移动 left 缩小窗口
            while (window.get(c1) > 1){
                char c2 = s.charAt(left);
                window.put(c2, window.getOrDefault(c2, 0) - 1);
                left++;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
// faster than 80.09% of Java


// 解法：优化版滑窗
// 当window中出现重复字符，不再是left一个个向右移动，而是直接index i=重复字符所在位置（重新起算）
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;         // 记录最长长度
        Map<Character, Integer> window = new HashMap<>();// 这里value不是频率，而是相应index
        
        // 尝试去拓展range[i, j]
        for (int i = 0, j = 0; j < n; j++){
            char ch = s.charAt(j);
            
            // 如果window中出现重复字符jth
            if (window.containsKey(ch))
                //skip the characters immediately
                //左边直接index i = 重复字符所在位置
                i = Math.max(i, window.get(ch));
            
            //如果属于新字符，增加output长度，把字符放入window中
            window.put(ch, j + 1);
            
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return maxLen;
    }
}
// faster than 88.98% of Java