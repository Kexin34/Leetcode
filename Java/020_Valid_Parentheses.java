// 简化版， faster than 98.70% of Java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);        
            if(c == '(' || c == '{' || c == '[')
                stack.push(c);
            else if (c == ')' || c == '}' || c == ']'){
                if(!stack.isEmpty() && leftOf(c) == stack.peek())
                    stack.pop();
                else
                    return false;// 和最近的左括号不匹配
            }
        }
        // 是否所有的左括号都被匹配了
        return stack.isEmpty();
    }
     private char leftOf(char c){
         if(c == '}') return '{';
         if(c == ')') return '(';
         return '[';
     }
}

