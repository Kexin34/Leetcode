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

