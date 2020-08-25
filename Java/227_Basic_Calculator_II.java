// 解法： Stack
class Solution {
    public int calculate(String s) {
        if(s == null || (s.length()) == 0) return 0;
        int res = 0, cur = 0, n = s.length();
        Stack<Integer> stack = new Stack<>();
        char op = '+';
        
        for(int i = 0; i < n; i++){
            if (Character.isDigit(s.charAt(i)))
                cur = cur * 10 + s.charAt(i) - '0';
            
            if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') 
                || i == n - 1){
                if (op == '+') stack.push(cur);
                if (op == '-') stack.push(cur * -1);
                if (op == '*') stack.push(stack.pop() * cur);
                if (op == '/') stack.push(stack.pop() / cur);
                op = s.charAt(i);
                cur = 0;
            } 
        }  
        // 栈中所有的数字都加起来就是最终结果了
        while (!stack.isEmpty())
            res += stack.pop();
        
        return res;
    }
}
// faster than 74.21% of Java
// Time complexity: O(n)
// Space complexity: O(n)