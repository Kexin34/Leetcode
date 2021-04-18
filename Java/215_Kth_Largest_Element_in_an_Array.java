// 优先队列
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element poll first'
        // 可写成PriorityQueue<Integer> pq = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++){
            pq.offer(nums[i]);
            if (pq.size() > k)
                pq.poll();
        }
        int res = pq.poll();
        return res;
    }
}
// Runtime: 4 ms, faster than 59.63% of Java 
// time: O(Nlogk)
// 在heap中添加一个元素是O(logk)，一共操作N次