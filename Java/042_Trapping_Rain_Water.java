// 暴力算法
class Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
        int res = 0;
        
        for (int i = 1; i < n - 1; i++){
            int l_max = 0, r_max = 0;
            // 找左边最高的柱子
            for (int j = i; j >= 0; j--)
                l_max = Math.max(l_max, height[j]);
            // 找右边最高的柱子
            for (int j = i; j < n; j++)
                r_max = Math.max(r_max, height[j]);
            // 如果自己就是最高的话，
            // l_max == r_max == height[i]
            res += Math.min(l_max, r_max) - height[i];
        }
        return res;
    }
}
// 时间复杂度 O(N^2)，空间复杂度 O(1)
// faster than 6.85% of Java


// 优化Mrthod 1: 备忘录优化
class Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
        int ans = 0;
        // 数组充当备忘录
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        
        // 从左向右计算 l_max
        for(int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for(int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for(int i = 1; i < n - 1; i++)
            ans += Math.min(l_max[i], r_max[i]) - height[i];
        return ans;
    }
}
// faster than 100.00% of Java,时间复杂度降低为 O(N)，已经是最优了
// 但是空间复杂度是 O(N)。双指针能够把空间复杂度降低到 O(1)


// 优化Mrthod 2: Two pointers
class Solution {
    public int trap(int[] height) {
        if(height.length == 0) return 0;
        int n = height.length;
        int left = 0, right = n - 1;// two pointers
        int ans = 0;
        
        int l_max = height[0];     
        int r_max = height[n - 1]; 
        
        while(left <= right){
            l_max = Math.max(l_max, height[left]);// l_max是height[0..left]中最高柱子的高度
            r_max = Math.max(r_max, height[right]);// r_max是height[right..end] 的最高柱子的高度
            
            // ans += min(l_max, r_max) - height[i]
            if(l_max < r_max){
                ans += l_max - height[left];
                left++;
            }else{
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }
}
//faster than 92.07% of Java
//Time complexity: O(n), 空间复杂度O(1)




