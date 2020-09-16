// 模板
/* 滑动窗口算法框架 */
void slidingWindow(string s, string t) {
    Map<Character, Integer> need, window;
    for (char c : t) need[c]++;

    int left = 0, right = 0;
    int valid = 0;
    while (right < s.size()) {
        // c 是将移入窗口的字符
        char c = s[right];
        // 右移窗口
        right++;
        // 进行窗口内数据的一系列更新
        ...

        /*** debug 输出的位置 ***/
        printf("window: [%d, %d)\n", left, right);
        /********************/

        // 判断左侧窗口是否要收缩
        while (window needs shrink) {
            // d 是将移出窗口的字符
            char d = s[left];
            // 左移窗口
            left++;
            // 进行窗口内数据的一系列更新
            ...
        }
    }
}

/*
    总结：和双指针题目类似，更像双指针的升级版，滑动窗口核心点是维护一个窗口集，根据窗口集来进行处理
    核心步骤
        right 右移
        收缩
        left 右移
        求结果

    需要变化的地方:
        1、右指针右移之后窗口数据更新
        2、判断窗口是否要收缩
        3、左指针右移之后窗口数据更新
        4、根据题意计算结果

    https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E6%8A%80%E5%B7%A7.md
 
    https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
 */

// 76. Minimum Window Substring
// 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        
        Map<Character, Integer> need = new HashMap<>();//// 保存需要的字符集
        Map<Character, Integer> window = new HashMap<>();// 保存滑动窗口字符集
        for (char c : t.toCharArray())
           need.put(c, need.getOrDefault(c, 0) + 1);
        
        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        int match = 0;                              // match匹配次数
        
        while(right < s.length()){
            // 1. 右指针右移之后窗口数据更新
            char c1 = s.charAt(right);  //若在t中，则放入窗口计数器
            if (need.containsKey(c1)){
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                //检查本字符在两个计数器中是否相等/match
                if (need.get(c1).intValue() == window.get(c1).intValue())
                    match++;
            }
            right++;
            // 2. 左指针右移
            // 当所有字符数量匹配之后（这时窗口已全部符合），开始移动left缩紧窗口    
            while (match == need.size()){//保证依旧符合
                if (right - left < minLen){
                    start = left;           //更新最小子串位置和其长度
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                // 左指针指向不在需要字符集则直接跳过
                if (need.containsKey(c2)){
                    window.put(c2, window.get(c2) - 1); //移出窗口
                    if (window.get(c2).intValue() < need.get(c2).intValue())
                        match--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}



// 567. Permutation in String
// 给定两个字符串  s1  和  s2，写一个函数来判断  s2  是否包含  **s1 **的排列。
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


// 438. Find All Anagrams in a String
// 给定一个字符串  **s **和一个非空字符串  p，找到  **s **中所有是  **p **的字母异位词的子串，返回这些子串的起始索引。
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() == 0 || p.length() == 0) return new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);
        
        int left = 0, right = 0;
        int match = 0;
        List<Integer> res = new ArrayList<>();
        
        while (right < sLen){
            char c1 = s.charAt(right);
            right++;
            if (need.containsKey(c1)){
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (need.get(c1).intValue() == window.get(c1).intValue())
                    match++;
            }
            // 当每个字符数量也匹配之后（这时窗口已全部符合），开始移动left缩紧窗口    
            while (match == need.size()){
                // 当窗口长度和字符串匹配，满足条件
                if (right - left == pLen) res.add(left);
                
                char c2 = s.charAt(left);
                left++;
                if (need.containsKey(c2)){
                    if (need.get(c2).intValue() == window.get(c2).intValue())
                        match--;
                    window.put(c2, window.get(c2) - 1);
                }
            }
        }
        return res;
    }
}




// 3. Longest Substring Without Repeating Characters
// 给定一个字符串，请你找出其中不含有重复字符的   最长子串   的长度。 示例  1:
// 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
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

















