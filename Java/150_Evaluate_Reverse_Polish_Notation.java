// 波兰表达式计算 > 输入: ["2", "1", "+", "3", "*"] > 输出: 9 解释: ((2 + 1) * 3) = 9
// 思路：用数组保存原来的元素，遇到+-*/取出运算，再存入结果，重复这个过程

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.parseInt(tokens[0]);
        
        Stack<Integer> stack = new Stack<>();
        
        for (String s : tokens) {
            // 遇到数字则压入栈中
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/"))
                stack.push(Integer.valueOf(s));
            else{
            //遇到符号，则把栈顶的两个数字拿出来运算，把结果再压入栈中
                int right = stack.pop();
                int left = stack.pop();
                if (s.equals("+")) stack.push(left + right);
                if (s.equals("-")) stack.push(left - right);
                if (s.equals("*")) stack.push(left * right);
                if (s.equals("/")) stack.push(left / right);
            }
        }
        return stack.pop();
    }
}
// faster than 58.68% of Java



// 递归 （不一定要会）
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.parseInt(tokens[0]);
        int op = (int) tokens.length - 1; //末尾必定是操作符
        return helper(tokens, op);
    }
     
    private int helper(String[] tokens, int op){
        String s = tokens[op];
        
        if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/"))
            return Integer.valueOf(s);
            
        int right = helper(tokens, --op);
        int left = helper(tokens, --op);
        
        if (s.equals("+")) return left + right;
        if (s.equals("-")) return left - right;
        if (s.equals("*")) return left * right;
        else return left / right;
    }
}