
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        
        // 如果是奇数
        if ((m + n) % 2 == 1)
            return getKth(nums1, 0, m - 1, nums2, 0, n - 1, (m + n) / 2 + 1);
        
        // 如果是偶数    
        int left = getKth(nums1, 0, m - 1, nums2, 0, n - 1, (m + n) / 2);
        int right = getKth(nums1, 0, m - 1, nums2, 0, n - 1, (m + n) / 2 + 1);
        return (left + right) / 2.0;
    }
    
    private int getKth(int[] A, int start1, int end1, int[] B, int start2, int end2, int k){
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        
        // 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2){
            return getKth(B, start2, end2, A, start1, end1, k);
        }
        // A数组排除完毕, 第k小一定存在于B数组，计算索引返回
        if (len1 == 0) 
            return B[start2 + k - 1];
        
        // 已经找到第k小的数
        if (k == 1)
            return Math.min(A[start1], B[start2]);
        
        // 开始二分(一半一半的排除)
        // 每次循环排除掉`k/2`个数，需要比较A[K/2]和B[K/2]来决定删谁
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (A[i] > B[j])            // B删掉前k/2个数
            return getKth(A, start1, end1, B, j + 1, end2, k - (j - start2 + 1));
        else
            return getKth(A, i + 1, end1, B, start2, end2, k - (i - start1 + 1));
    }
}

// Runtime: 2 ms, faster than 99.81% of Java 
// 时间复杂度：O(log(m+n))。`m`和`n`分别是两个数组的长度。二分法的复杂度为O(log(m+n))。
// 空间复杂度：O(1)