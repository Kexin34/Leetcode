// 双指针

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;
        
        while (left < right) {
            // 当前面积 = 左右指针较短高度 * 左右的宽
            int area = Math.min(height[left], height[right]) * (right - left);
            max = Math.max(max, area);
            
            // 因为宽度只会越来越小，优先移动较短的木板，保留较高的
            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return max;
    }
}
// Runtime: 3 ms, faster than 76.34% of Java 