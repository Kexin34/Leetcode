// 九章强化-3： MIN / MAX heap
// 维持max heap为前半部分，min heap为后半部分

class MedianFinder {
    // maxHeap存前半部分， minHeap存后半部分
    private PriorityQueue<Integer> maxHeap, minHeap;
    private int numOfElements = 0;
    
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));
        minHeap = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (numOfElements % 2 == 0){ //当前是偶数个 eg:[123][456], add 7
            minHeap.offer(num);  // 先添加到后半部分 [123][4567]
            maxHeap.offer(minHeap.poll());// 把后半最小的移动一个到前半[1234][567]
        }else{
            maxHeap.offer(num); // 先放入前半，[1245][678], add:3 -> [12345][678]
            minHeap.offer(maxHeap.poll());// 左边把最大的移动到右半部分[1234][5678]
        }
        numOfElements++;
    }

    public double findMedian() {
        if(numOfElements % 2 == 1)
            return maxHeap.peek();
        else
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
// Runtime: 46 ms, faster than 78.99% of Java
// Time: find: O(1），addNum：添加为O(logk) k是堆里的元素数量