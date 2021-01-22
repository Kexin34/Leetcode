// 解法：单调栈

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>(); //存的是idx
        
        for (int i = 0; i < n * 2; i++){
            int curNum = nums[i % n];      //当前循环到的元素值
            while (!stack.isEmpty() && curNum > nums[stack.peek()]){
                int preIdx = stack.pop();
                res[preIdx] = curNum;
            }
            stack.push(i % n);
        }
        return res;
    }
}
// faster than 68.93% of Java


// 套用单调栈模板：
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // 假装这个数组长度翻倍了
        for (int i = 2 * n - 1; i >= 0; i--){
            // 索引要求模，其他的和模板一样
            while (!stack.isEmpty() && nums[i % n] >= stack.peek())
                stack.pop();
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }
}
