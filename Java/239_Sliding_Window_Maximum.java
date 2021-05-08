// 花花酱的单调队列解法
// Time complexity: O(n)
// Space complexity: O(k)

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return nums;
        
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> monoque = new LinkedList<>();
        
        for (int i = 0; i < nums.length; i++){
            // 如果当前比队列头部大，push到单调队列队尾(队头最大)
            // 重点：push一个元素的时候，会把队列里面所有比它小的元素都删掉
            while (monoque.size() > 0 && nums[i] >= nums[monoque.getLast()])
                monoque.removeLast();
            monoque.addLast(i);
            
            //如果是在滑窗范围内，把队列头（max）放入答案
            if (i - k + 1 >= 0)
                res[i - k + 1] = nums[monoque.getFirst()];
            
            // 当滑窗最左边index比queue中最大的元素index还要大，说明最大值已经失效了
            if (i - k + 1 >= monoque.getFirst())
                monoque.removeFirst();
        }
        return res;
    }
}
// faster than 78.87% of Java




/* ------------ 用linkedlist实现单调队列 ------- */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            //先填满窗口的前 k - 1
            if (i < k - 1)
                window.push(nums[i]);
            else {
                window.push(nums[i]);  // 窗口向前滑动，加入新数字
                res.add(window.max());  // 记录当前窗口的最大值
                window.pop(nums[i - k + 1]);  // 移出旧数字
            }
        }
        // 需要转成 int[] 数组再返回
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = res.get(i);
        return ans;
    }
    
    /* 单调队列的实现 */
    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();
        
        public void push(int n) {
            // 将小于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n){
                q.pollLast();
            }
            // 然后将 n 加入尾部
            q.addLast(n);
        }
        
        public int max() {
            return q.getFirst();
        }
        
        public void pop(int n) {
            if (q.getFirst() == n) 
                q.pollFirst();
        }
    }
}
// Runtime: 36 ms, faster than 30.32% of Java





// 和上面一样的思路，只是用了Deque来实现单调队列
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





// 九章
// Deque, 维护单调递减。重要的是需要add和delete
// frist[77321]last
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        if (k == 0) return res;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        // 初始化queue
        for (int i = 0; i < k - 1; i++)
            inQueue(deque, nums[i]);
        
        for (int i = k - 1; i < n; i++){
            inQueue(deque, nums[i]);   // 添加
            res[i + 1 - k] = deque.peekFirst();  // 单调递减队列的头部，一定最大
            outQueue(deque, nums[i - k + 1]); // 移除
        }
        return res;
    }
    
    // 从尾部添加
    void inQueue(Deque<Integer> deque, int num){
        while (!deque.isEmpty() && deque.peekLast() < num)
            deque.pollLast();
        deque.offer(num);
    }
    
    // 从头部移除，如果头部不是目标，直接略过
    void outQueue(Deque<Integer> deque, int num){
        if (deque.peekFirst() == num)
            deque.pollFirst();
    }
}
// Runtime: 34 ms, faster than 32.14% of Java