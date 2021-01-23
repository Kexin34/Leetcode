// 解法一： （相对粗暴） map 来处理映射

class Solution {
    public boolean isIsomorphic(String s, String t) {
        // 需要验证 s - > t 和 t -> s 两个方向, 调用两次
        return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
    }
    
    private boolean isIsomorphicHelper(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            // 如果 map[c1] 不存在，那么就将 c1 映射到 c2 ，即 map[c1] = c2
            if (!map.containsKey(ch1))
                map.put(ch1, ch2);
            else {
            	// map[c1] 存在，那么就判断 map[c1] 是否等于 c2，
            	// 也就是验证之前的映射和当前的字母是否相同。
                if (ch2 != map.get(ch1)) 
                	return false;
            }
        }
        return true;
    }
}
//  faster than 75.69% of Java



// 解法二：
// 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int len = s.length();
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        for (int i = 0; i < len; i++){
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            //当前的映射值是否相同
            if (mapS[c1] != mapT[c2])
                return false;
            else {
                mapS[c1] = i + 1;
                mapT[c2] = i + 1;
            }
        }
        return true;
    }
}
//  faster than 99.42% of Java