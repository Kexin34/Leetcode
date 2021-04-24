// 需要保持window里面是从小到大排列的，这样在窗里找中位数很方便
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];
        if (k == 0) return res;
        
        // 建立一个长度为k的窗口
        int[] window = new int[k];
        // 初始化窗口并sort
        for (int i = 0; i < k; i++)
            window[i] = nums[i];
        Arrays.sort(window);
        
        // 开始滑窗，每次滑动都计算中位数放到res，并且remove最老的(i-k)，把新的数[i]insert到窗口（保持sort）
        for (int i = 0; i + k <= n; i++){
            res[i] = ((double) window[k / 2] + window[(k - 1) / 2]) / 2;
            if (i + k == n) break;   // 最后一次计算，只需要算中位，不用再插入和移除
            remove(window, nums[i]);
            insert(window, nums[i + k]);
        }
        return res;
    }
    
    // Remove val from window 并保证窗口从小到大，从删除位置右侧都向左移动
    // eg: [-1,1,3] remove 1 --> [-1,3,3]
    private void remove(int[] window, int val){
        int i = 0;
        int len = window.length;
        while (i < len && val != window[i])  // i去定位val在窗中位置
            i++;
        while (i < len - 1)
            window[i] = window[++i];
    }
    
    // Insert val into window, window[k - 1] is 左边一位的重复 before inseration
    // 找到插入的位置i，然后i右侧的所有元素从最后开始都右移一一位，最后把val插入到i的位置
    private void insert(int[] window, int val){
        int wLen = window.length;
        int i = 0;
        while (i < wLen - 1 && val > window[i])
            i++;
        int j = wLen - 1;
        while (i < j){
            window[j] = window[--j];
        }       
        window[j] = val;
    }
}
// Runtime: 72 ms, faster than 32.92% of Java
// Time: O((n - k + 1) * k), 遍历n - k + 1次，每次O(K)移动




