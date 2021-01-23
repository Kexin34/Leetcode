// 解法：哈希表/字符串来映射字母出现的次数

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] cnts = new int[26];
        
        for (char ch : s.toCharArray()){
            cnts[ch - 'a']++;
        }
        for (char ch : t.toCharArray()){
            cnts[ch - 'a']--;
        }
        // 若cnts[i]为零，说明这个index对应的数字要么两个string都没出现，
        // 要么出现次数相同，一加一减抵消
        for (int cnt : cnts){
            if (cnt != 0)
                return false;
        }
        return true;
    }
}
// faster than 100.00% of Java
// Time: O(N), Space: O(1)