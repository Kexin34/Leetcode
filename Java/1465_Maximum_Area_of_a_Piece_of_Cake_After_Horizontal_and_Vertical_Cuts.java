

class Solution {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int m = horizontalCuts.length, n = verticalCuts.length;

        // 先把所有切法排序
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        // 起初的max 先设定为最靠两边的切线距离对面边界的距离
        int mx = Math.max(verticalCuts[0], w - verticalCuts[n - 1]);
        int my = Math.max(horizontalCuts[0], h - horizontalCuts[m - 1]);
        
        // 遍历所有相邻切法，max更新为为两个相邻切法的最大距离
        for (int i = 1; i < n; i++)
            mx = Math.max(mx, verticalCuts[i] - verticalCuts[i - 1]);
        for (int i = 1; i < m; ++i)
            my = Math.max(my, horizontalCuts[i] - horizontalCuts[i - 1]);  
        
        // 1L is long type.have to change the expression to long type to avoid int overflow
        return (int) (mx * 1L *  my % (Math.pow(10, 9) + 7));
    }
}

// faster than 95.72% of Java
// Time complexity: O(nlogn)
// Space complexity: O(1) if sort in place otherweise O(n)



