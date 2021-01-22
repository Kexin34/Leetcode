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
        Deque<Integer> stack = new ArrayDeque<Integer>();// 存index位置
        
        int[] newHeights = new int[len + 2];
        for (int i = 0; i < len; i++)
            newHeights[i + 1] = heights[i];
        
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);
        
        for (int i = 1; i < len + 2; i++) {
             while (newHeights[i] < newHeights[stack.peekLast()]){
                int height = newHeights[stack.pollLast()];
                int width = i - stack.peekLast() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.addLast(i);
        }
        return maxArea;
    }
}