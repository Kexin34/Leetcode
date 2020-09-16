// 解法：滑动窗口（模板）
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
// faster than 36.40% of Java



// 解法：优化，只用一个array，而不是两个哈希表
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() == 0 || p.length() == 0) return new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        int left = 0, right = 0;
        int match = map.size();
        int head = 0;
        int len = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        
        while (right < sLen){
            char c1 = s.charAt(right);
            right++;
            if (map.containsKey(c1)){
                map.put(c1, map.get(c1) - 1);
                if (map.get(c1) == 0) 
                    match--;
            }
            // 当每个字符数量也匹配之后（这时窗口已全部符合），开始移动left缩紧窗口    
            while (match == 0){
                char c2 = s.charAt(left);
                if (map.containsKey(c2)){
                    map.put(c2, map.get(c2) + 1);
                    if(map.get(c2) > 0)
                        match++;
                }
                // 当窗口长度和字符串匹配，满足条件
                if (right - left == pLen) res.add(left);
                left++;
            }
        }
        return res;
    }
}
// // faster than 36.40% of Java



// 解法：滑窗优化
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        for (char c : p.toCharArray()) 
            hash[c]++;
    
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right)] >= 1)
                count--;
            hash[s.charAt(right)]--;
            right++;
            
            
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);
            
            //if we find the window's size = p, then move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length()){
                if (hash[s.charAt(left)] >= 0) 
                    count++;
                hash[s.charAt(left)]++;
                left++;
            }
            
        }
        return list;   
    }
}
//  faster than 60.82% of Java
// Time Complexity: O(n), the "start" and "end" points will only move from left to right once.

