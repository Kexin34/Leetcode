// 解法：单调栈

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int curIdx = 0; curIdx < n ; curIdx++){
            // 一旦当前元素大于栈顶元素，取出栈顶并操作
            while(!stack.isEmpty() && T[curIdx] > T[stack.peek()]){
                int preIdx = stack.pop();
                res[preIdx] = curIdx - preIdx;
            }
            stack.push(curIdx);
        }
        return res;
    }
}
//  faster than 84.56% of Java


// 单调栈模板（更好理解）
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();// 这里放元素索引，而不是元素
        
        for (int i = n - 1; i >= 0; i--){
            while (!stack.isEmpty() && T[i] >= T[stack.peek()])
                stack.pop();
            // 得到索引间距
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
//  faster than 52.94% of Java