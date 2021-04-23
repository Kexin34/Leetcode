// Keep track of the k biggest elements in the minimum priority queue 
// 最小优先队列，无论入队顺序，当前最小的元素优先出队
// 维护 k 大小的最小堆（堆顶到最后是从小到大），这样peek堆顶就是第k大（1st~k-1th都在堆里面）


class KthLargest {
    PriorityQueue<Integer> pq;
    int k = 0;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();   // min heap
        for (int i : nums)
            pq.offer(i);
    }
    
    public int add(int val) {
        pq.offer(val);
        while (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
// Runtime: 18 ms, faster than 35.12% of Java
