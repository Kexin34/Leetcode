// 啰嗦解法，但是高效，faster than 98.70% of Java
class Solution {  
    // Hash table for the mapping
    private HashMap<Character, Character> mappings;
    
    //Initialize hash map with mappings, makes the code easier to read
    public Solution(){
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put('(', ')');
        this.mappings.put('{', '}');
        this.mappings.put('[', ']');
    }
    
    public boolean isValid(String s) {
        //Initialize a stack 
        Stack<Character> stack = new Stack<Character>();
        
        //proceed each character c at a time, from string s
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            
            //If the current character is a closing bracket.
            if(this.mappings.containsValue(c)){
                //Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();
                // If this closing bracket doesn't match the stack's top element. return false.
                if (topElement != c)
                    return false;   
            }else
                //If char is an opening bracket, push its correspoding closing bracket to the stack.
                stack.push(mappings.get(c));
        }
        
        // At the end, if the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
}

// 同样思路，简化版， faster than 98.70% of Java
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

