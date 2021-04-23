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
// faster than 49.29% of Java




// 本题是“向前型指针”题目，可以用窗口类指针移动模板来解
class Solution {
    public String minWindow(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        String minStr = "";
        int[] hash_s = new int[256];
        int[] hash_t = new int[256];
        
        // Init target hashmap
        for (char ch: t.toCharArray())
            hash_t[ch]++;
        
        int i = 0, j = 0;
        for (i = 0; i < s.length(); i++){
            while (j < s.length() && !isContain(hash_s, hash_t)){
                hash_s[s.charAt(j)]++;
                j++;
            }
            // j首次出界，所以长度计算不用加一
            if (isContain(hash_s, hash_t)){
                if (j - i < minLen){
                    minStr = s.substring(i, j);
                    minLen = j - i;
                }
            }
            // 收缩左边界
            hash_s[s.charAt(i)]--;
        }
        return minStr;
    }
    
    private boolean isContain(int[] hash_s, int[] hash_t){
        for (int i = 0; i < 256; i++){
            if (hash_t[i] > hash_s[i])
                return false;
        }
        return true;
    }
}
// Runtime: 51 ms, faster than 12.34% of Java
// time: O(256*N)



