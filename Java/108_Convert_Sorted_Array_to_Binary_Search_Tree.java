// 递归
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1); //对递增数组进行拆分并进行递归调用。
    }
    
    public TreeNode build(int[] nums, int start, int end){
        if (start > end) return null;   //递归end case
        
        int m = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[m]); //找到中点作为根节点
        root.left = build(nums, start, m - 1); //左侧数组作为左子树
        root.right = build(nums, m + 1, end);  //右侧数组作为右子树
        return root;
    }
}
// faster than 100.00% of Java 
// Time complexity: O(n)
// Space complexity: O(logn)

