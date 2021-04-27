// Fenwick Tree
// Time complexity:
// init O(nlogn), n个数字，每个数字做logn的计算
// query: O(logn)
// update: O(logn)

class NumArray {
    FenwickTree tree_;
    int[] nums_;
    
    public NumArray(int[] nums) {
        nums_ = nums;
        tree_ = new FenwickTree(nums.length);
        for (int i = 0; i < nums.length; i++)
            tree_.update(i + 1, nums[i]);  // 从1开始，所以i+1
    }
    
    public void update(int index, int val) {
        tree_.update(index + 1, val - nums_[index]);
        nums_[index] = val;
    }
    
    public int sumRange(int left, int right) {
        // 从j这个位置到i - 1这个位置，因为tree是从1开始所以加一
        return tree_.query(right + 1) - tree_.query(left);
    }
    
    class FenwickTree {
        int sums_[];
        public FenwickTree(int n) {
            sums_ = new int[n + 1]; // 从1开始
        }
        public void update(int i, int delta) {
            while (i < sums_.length) {
                sums_[i] += delta;
                i += i & -i;  // lowbit计算
            }
        }
        
        public int query(int i) {
            int sum = 0;
            while (i > 0){
                sum += sums_[i];
                i -= i & -i;
            }
            return sum;
        }
     }
}
// Runtime: 61 ms, faster than 92.18% of Java

