// 解法一：递归，找出所有组合
class Solution {
    Set<Integer> set = new HashSet<>();
    
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
            
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}
// faster than 98.12% of Java
// time: O(N), Space: O(N)



// 解法二： DFS中序遍历得到有序数组 + 双指针查找
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        
        int i = 0;
        int j = nums.size() - 1;
        // 利用双指针对有序数组进行查找
        while (i < j) {
            int sum = nums.get(i) + nums.get(j);
            if (sum == k) return true;
            if (sum < k) i++;
            else j--;
        }
        return false;
    }
    
    public void inOrder(TreeNode root, List<Integer> nums){
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }
}
// faster than 98.12% of Java













// 解法n：先通过中序遍历，得到有序数组，然后进行正常sum（set）查找
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inOrder(root, nums);
        int len = nums.size();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            int n = nums.get(i);
            if (set.contains(k - n)) return true;
            set.add(n);
        }
        return false;
    }
    
    public void inOrder(TreeNode root, List<Integer> nums){
        if (root == null) return;
        inOrder(root.left, nums);
        nums.add(root.val);
        inOrder(root.right, nums);
    }
}
//  faster than 32.20% of Java