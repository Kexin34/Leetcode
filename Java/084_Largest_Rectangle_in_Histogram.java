//暴力解：（超时）
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int res = 0;
        for (int i = 0; i < len; i++){
            int curHeight = heights[i];
            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            while (left > 0 && heights[left - 1] >= curHeight)
                left--;
            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight)
                right++;
            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }
}
// 超时错误


// 解法：单调栈 + 哨兵
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        if (len == 1) return heights[0];
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();// 存index位置
        
        int[] newHeights = new int[len + 2];// 拓展了heights两边，多出一个0节点（哨兵）
        for (int i = 0; i < len; i++)
            newHeights[i + 1] = heights[i];
        
        // 先放入哨兵，在循环里就不用做非空判断
        stack.push(0);
        
        for (int i = 1; i < len + 2; i++) {
            //newHeights[stack.peek()]是左边柱子的高度，stack.peek()返回index
            // 找到一个pop index对应高度比当前高度小的时候停止，计算高度&宽度，把当前压入
            while (newHeights[stack.peek()] > newHeights[i]){
                int height = newHeights[stack.pop()];  //由低点决定
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }
}
// Runtime: 25 ms, faster than 68.60% of Java 
// 时间复杂度：O(N)，输入数组里的每一个元素入栈一次，出栈一次。
// 空间复杂度：O(N)，栈的空间最多为 N。



// 九章强化-stack
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();// 存index位置
        
        for (int i = 0; i <= len; i++) {
            int cur = (i == len) ? -1 : heights[i];
            while (!stack.isEmpty() && cur <= heights[stack.peek()]){
                int h = heights[stack.pop()]; 
                int w = stack.isEmpty() ?  i: i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}
// Runtime: 25 ms, faster than 68.60% of Java 