// priority queue保留最大的k个, 原数组内直接比较放入pq，不需要在存距离（不需要哈希表）
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        // pq规则：
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
            (p1, p2) -> (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1])
        );
        
        // 把point放入pq，pa保留最大的k个
        for (int[] p : points){
            pq.offer(p);
            if (pq.size() > K)
                pq.poll();
        }
        // 把pa内容(先poll小的）放入result数组后面，从后往前放
        int[][] res = new int[K][2];
        while (K > 0)
            res[--K] = pq.poll();       //注意不能是[K--]！！
        
        return res;
    }
}
// faster than 26.03% of Java
// time complexity : O(NlogK), 




// 最优方法： quick select解法
//（320上课教授讲的是quick select递归版，这里是遍历版）

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int l = 0, r = len - 1;
        while (l <= r){
            int mid = quickSelectSwap(points, l, r);
            if (mid == K) break;
            if (mid < K)
                // all element on the left of the pivot are all proper candidates but it is
                // not adequate, we have to do the same thing on right side
                l = mid + 1;
            else
                r = mid - 1;
        }
        // 此时points已经是soted好的了，直接返回前K个元素
        return Arrays.copyOfRange(points, 0, K);
    }
    
    private int quickSelectSwap(int[][] A, int l, int r){
        int[] pivot = A[l];
        while (l < r){
            // 比pivot大的point element都跑去右边
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            // 把比pivot小的point element都跑去左边
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }
    
    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
// faster than 99.43% of Java
// Time complexity: O(nlogn)
// Space complexity: O(n)
