// 本题是“向前型指针”题目，可以用【窗口类指针移动模板】来解

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int i = 0, j = 0;
        char c;
        
        for (i = 0; i < s.length(); i++){
            while (j < s.length()){
                c = s.charAt(j);
                if (map.containsKey(c)){  // 满足条件
                    map.put(c, map.get(c) + 1);
                }else{
                    if (map.size() == 2)
                        break;
                    map.put(c, 1);
                }
                j++;
            }
            // j 是首次出界，所以不用+1
            maxLen = Math.max(maxLen, j - i);
            
            // 去收缩左边界(收缩窗口map i对应)
            c = s.charAt(i);
            if (map.containsKey(c)){
                int count = map.get(c);
                if (count > 1)
                     map.put(s.charAt(i), count - 1);
                else
                    map.remove(c);
            }
           
        }
        return maxLen;
    }
}
// Runtime: 5 ms, faster than 51.10% of Java