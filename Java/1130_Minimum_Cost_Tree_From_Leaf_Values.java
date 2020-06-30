//https://www.youtube.com/watch?v=xcYkzSrgOmY

class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : arr) {
            while (stack.peek() <= a) {
                // drop value is the value smaller than it's both side
                int drop = stack.pop();
                res += drop * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }

        //此时stack里面为状态一（从大到小），需要从右往左（从小到大）一路乘上去
        while (stack.size() > 2) {
            // make the bigger number be used as less as possible
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}

//faster than 96.26% of Java 