// When get right-subtree node, pop all left-subtree nodes..
// Complexity: Time O(n), Space O(n)

class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        
        for (int cur: preorder){
            if (cur < low) return false;

            // 遇到的数字比栈顶元素大，那么就是右边的值了
            // 更新 low 值并删掉栈顶元素，然后继续和下一个栈顶元素比较
            while (!stack.isEmpty() && cur > stack.peek()) 
                low = stack.pop();
            
            // 遇到的数字比栈顶元素小，说明是其左子树的点, 继续压入栈中
            stack.push(cur);
        }
        return true;
    }
}
// 7 ms, faster than 76.49% of Java 
