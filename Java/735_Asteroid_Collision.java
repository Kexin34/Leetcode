// 解法：利用stack来模拟

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++){
            int size = asteroids[i];
            if (size > 0)                       // To right, OK
                stack.push(size);
            else{                               // To left
                // OK when all are negtives
                if (stack.isEmpty() || stack.peek() < 0)
                    stack.push(size);
                
                // Top of the stack is going right.
                else if (Math.abs(stack.peek()) <= Math.abs(size)){
                    // Its size is less than the current one.
                    // The current one still alive!
                    if (Math.abs(stack.peek()) < Math.abs(size))
                        --i;                // keep the current one for next loop
                    stack.pop();
                }
                // if current one is less than stack top, skip current one
            }
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--)
            res[i] = stack.pop();
        return res;
    }
}
// faster than 86.51% of Java 
// Time complexity: O(n), Space complexity: O(n)


