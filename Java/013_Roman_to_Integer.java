// 解法： 哈希表 + 前后规则
// 第一，如果当前数字是最后一个数字，或者之后的数字比它小的话，则加上当前数字。
// 第二，其他情况则减去这个数字。
class Solution {
    
    static Map<Character, Integer> map = new HashMap<>();
    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }
    
    public int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            int val = map.get(s.charAt(i));
            if (i == s.length() - 1 || map.get(s.charAt(i)) >= map.get(s.charAt(i + 1)))
                res += val;
            else
                res -= val;
        }
        return res;
    }
}
// faster than 75.90% of Java
