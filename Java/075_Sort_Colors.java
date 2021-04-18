
/*
简单答法：执行两次partition array就可以：先把左 和中-右分开，
再把中和右分开

更优化解法：还是两个指针+1个新指针。
如果能把L指针左边的都换为0，R指针右边的都为2，那么中间的都一定是1。
用i指针从左向右遍历，碰到0就往l指针丢，碰到2就往R指针丢。
*/
class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        
        int pl = 0;  //for "0"
        int pr = nums.length - 1; // for "2"
        int i = 0;   // for "1"
        
        while (i <= pr){
            if (nums[i] == 0){
                swap(nums, pl, i);
                pl++;
                i++;
            }else if (nums[i] == 1){
                i++;
            }else{
                swap(nums, pr, i);
                pr--;
            }
        }
    }
    
    private void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
// Runtime: 0 ms, faster than 100.00% of Java
// O(N)
