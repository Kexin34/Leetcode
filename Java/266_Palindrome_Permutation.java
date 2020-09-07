// 解法一：统计奇数
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s.length() == 0) return false;
        int count = 0;
        
        // 建立每个字母和其出现次数的映射
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        
        // 遍历 HashMap，统计出现次数为奇数的字母的个数
        for (int freq : map.values())
            if (freq % 2 == 1) count++;
        
        // 只有两种情况是回文数，第一种是没有出现次数为奇数的字母，
        // 再一个就是字符串长度为奇数，且只有一个出现次数为奇数的字母
        return count == 0 || (s.length() % 2 == 1 && count == 1);
    }
}
// faster than 100.00% of Java


// class Solution {
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s.length() == 0) return false;
        Set <Character> set = new HashSet<>();
        
        for (char c : s.toCharArray()){
            if (!set.contains(c))
                set.add(c);
            else        //字母已经存在，我们删除该字母
                set.remove(c);
        }
        // 最终如果 HashSet 中没有字母或是只有一个字母时，说明是回文串
        return set.isEmpty() || set.size() == 1;
    }
}
// faster than 100.00% of Java