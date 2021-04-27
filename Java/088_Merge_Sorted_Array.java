class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int[] aux = new int[m];
        for (int i = 0; i < m; i++) aux[i] = nums1[i];
        
        // Two get pointers for nums1_copy and nums2.
        int i = 0, j = 0;
        // Set pointer for nums1
        int k = 0;
        
        // Compare elements from nums1_copy and nums2
        while ((i < m) && (j < n))
            nums1[k++] = (aux[i] < nums2[j]) ? aux[i++] : nums2[j++];
        
        // if there are still elements to add
        if (i < m)
            while (i < m) nums1[k++] = aux[i++];
        if (j < n)
            while (j < n) nums1[k++] = nums2[j++];
    }
}
// faster than 100.00% of Java
// Time complexity : O(n+m).
// Space complexity : O(m).