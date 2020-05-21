// Source: https://leetcode.com/problems/sliding-window-maximum/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
			return new int[0];
		}
        Monoqueue window = new Monoqueue();
        int [] res = new int[nums.length - k + 1];
        
        int idx = 0;
        for (int i = 0; i< nums.length; i++){
            if (i < k - 1)      //先填满窗口的前 k - 1
                window.push(nums[i]);
            else{               // 窗口向前滑动
                window.push(nums[i]);
                res[idx++] = window.max();
                window.pop(nums[i - k + 1]);
            }
        }
        return res;
    }
    
    public class Monoqueue{
        Deque<Integer> data = new ArrayDeque<>();

        /*单调队列的 push 方法依然在队尾添加元素，但是要把前面比新元素小的元素都删掉*/
        public void push(Integer n){
            while(!data.isEmpty() && data.peekLast().compareTo(n) < 0) 
                data.pollLast();
            data.offerLast(n);
        }
        //因为push操作，元素大小保持一个单调递减的顺序
        public Integer max(){
            return data.peekFirst();
        }
        //pop要判断相等，因为想删除的队头元素 n 可能已经被「压扁」了
        public void pop(int n){
            if (!data.isEmpty() && n == data.peekFirst()) 
                data.pollFirst();
        }
    }
}