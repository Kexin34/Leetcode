//  solving from the end, which avoid extra moving manipulation.
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int k = m + n - 1;
        
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2])
                nums1[k--] = nums1[p1--];
            else
                nums1[k--] = nums2[p2--];
        }
        
        while (p2 >= 0){   //only need to combine with remaining nums2
            nums1[k--] = nums2[p2--];
        }
    }
}
//  0 ms, faster than 100.00% of Java