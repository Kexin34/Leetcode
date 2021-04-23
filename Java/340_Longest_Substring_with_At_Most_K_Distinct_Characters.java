// 本题是“向前型指针”题目，可以用【窗口类指针移动模板】来解


class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int i = 0, j = 0;
        char c;
        
        for (i = 0; i < s.length(); i++){
            while (j < s.length()){
                c = s.charAt(j);
                if (map.containsKey(c))
                    map.put(c, map.get(c) + 1);
                else {
                    if (map.size() == k)
                        break;
                    map.put(c, 1);
                }
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
            // 左边回退，操作window hash对应的i
            c = s.charAt(i);
            if (map.containsKey(c)){
                int count = map.get(c);
                if (count > 1)
                    map.put(c, count - 1);
                else
                    map.remove(c);
            }
        }
        return maxLen;
    }
}
// Runtime: 7 ms, faster than 53.47% of Java 
// Time: I believe is O(N)