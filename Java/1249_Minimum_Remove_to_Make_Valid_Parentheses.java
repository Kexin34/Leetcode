// 最基础solution： 用Stack 和 String Builder
// 两步：
// 1. Identify all indexes that should be removed.
// 2. Build a new string with removed indexes.
class Solution {
    public String minRemoveToMakeValid(String s) {
        // set: keep track of the unmatched ")" we come across.
        Set<Integer> indexesToRemove = new HashSet<>();
        // stack里面压的是s的index
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(')
                stack.push(i);
            else if (s.charAt(i) == ')'){
                if (stack.isEmpty())
                    indexesToRemove.add(i);
                else
                    stack.pop();
            }
        }
        
        // now all the indexes on the stack at the end are the indexes of the unmatched "(".
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty())
            indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        // Iterate over the string and insert each character we want to keep into a StringBuilder. 
        for (int i = 0; i < s.length(); i++){
            if (!indexesToRemove.contains(i))
                sb.append(s.charAt(i));
        }
        // Then once we have all the characters, it is a single O(n) step to convert them into a string.
        return sb.toString();
    }
}
// faster than 60.38% of Java 
// Time complexity : O(n)
// 复杂度分析请看https://leetcode.com/articles/minimum-remove-to-make-valid-parentheses/
