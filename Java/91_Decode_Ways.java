
// 记忆化递归（非最优解）（pass string）
class Solution {
    Map<String, Integer> memo = new HashMap<>();
    public int numDecodings(String s) {
        memo.put("", 1);
        return ways(s);
    }
    
    public int ways(String s){
        if (memo.getOrDefault(s, 0) != 0) return memo.get(s);
        
        // base case
        if (s.charAt(0) == '0') return 0;  // 不能以0开头
        if (s.length() == 1) return 1;     // str只剩一个字符，可以返回1种方式
        
        // 去掉单个字符后可能性 + 去掉两个字符(若合法）可能性
        int w = ways(s.substring(1));
        
        int prefix = Integer.valueOf(s.substring(0, 2));
        if (prefix <= 26)       // 查看两个字符是否合法
            w += ways(s.substring(2));
        
        memo.put(s, w);
        return w;
    }
}
// faster than 5.01% of Java
// Time complexity: O(n^2)
// Space complexity: O(n^2)


// 记忆化递归 （优化后）
class Solution {
    Map<Integer, Integer> memo = new HashMap<>();
    
    public int numDecodings(String s) {
        return ways(s, 0);
    }
    
    public int ways(String s, int l){
        if (memo.containsKey(l)) return memo.get(l);
        
        // base case
        if (l == s.length()) return 1; // reach到原str末尾（empty），返回1作为成功
        if (s.charAt(l) == '0') return 0;  // 不能以0开头
        if (l == s.length() - 1) return 1; // str只有一个字符，可以返回1种方式
        
        // 去掉单个字符后可能性 + 去掉两个字符(若合法）可能性
        int w = ways(s, l + 1);
        
        int prefix = (s.charAt(l) - '0') * 10 + s.charAt(l + 1) - '0';
        if (prefix <= 26)       // 查看两个字符是否合法
            w += ways(s, l + 2);
        
        memo.put(l, w);
        return w;
    }
}
// faster than 99.00% of Java
// Time complexity: O(n)
// Space complexity: O(n)





// DP 优化后（滚动变量）
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;
        
        int n = s.length();
        int w1 = 1;
        int w2 = 1;
        
        for (int i = 1; i < n; i++){
            int way = 0;
            if (!isValid(s.charAt(i)) && !isValid(s.charAt(i - 1), s.charAt(i)))
                return 0;
            if (isValid(s.charAt(i))) way = w1;
            if (isValid(s.charAt(i - 1), s.charAt(i))) way += w2;
            
            w2 = w1;
            w1 = way;
        }
        return w1;
    }
    
    public boolean isValid(char c) {return c != '0';}
    
    public boolean isValid(char c1, char c2){
        int num = (c1 - '0') * 10 + c2 - '0';
        return num >= 10 && num <= 26;
    }
}
// faster than 99.00% of Java
// Time complexity: O(n)
// Space complexity: O(1)

