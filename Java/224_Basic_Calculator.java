class Solution {
    public int calculate(String s) {
        int operand = 0;
        int result = 0; // For the on-going result
        // 记录算式中的数字
        Stack<Object> stack = new Stack<Object>();
        int sign = 1;  // 1 means positive, -1 means negative
        int srtLen = s.length();
        
        for(int i = 0; i < srtLen; i++){
            char ch = s.charAt(i);
            // 如果是数字，连续读取到 num
            if (Character.isDigit(ch)) 
                operand = 10 * operand + (int) (ch - '0');
            // 如果不是数字，就是遇到了下一个符号，
            // 之前的数字和符号就要存进栈中
            else if(ch == '+'){
                result += sign * operand;
                sign = 1;
                operand = 0;
            }else if (ch == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            }else if (ch == '(') {
                // Push the result and sign on to the stack, for later
                // We push the result first, then sign
                stack.push(result);
                stack.push(sign);
                // Reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1;
                result = 0;
            }else if (ch == ')') {
                /* ')' marks end of expression within a set of parenthesis
                 * Its result is multiplied with sign on top of stack
                 * as stack.pop() is the sign before the parenthesis
                 */
                result += sign * operand;
                result = result * (int)stack.pop();
                /* Then add to the next operand on the top.
                 * as stack.pop() is the result calculated before this parenthesis
                 * (operand on stack) + (sign on stack * (result from parenthesis))
                 */
                result += (int)stack.pop();
                operand = 0;
            }
        }
        return result + (sign * operand); 
    }
}