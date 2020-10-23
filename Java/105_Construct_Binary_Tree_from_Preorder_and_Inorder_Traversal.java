// 套用模板 （106也是）
class Solution {
    int pre_idx;
    int[] preorder;
    int[] inorder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        this.preorder = preorder;
        this.inorder = inorder;
        pre_idx = 0;
        for (int i = 0; i < inorder.length; i++)
            idx_map.put(inorder[i], i);
        return helper(0, inorder.length - 1);
    }
    
    public TreeNode helper(int in_left, int in_right){
        if (in_left > in_right || pre_idx == preorder.length) return null;
        
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);
        int index = idx_map.get(root.val);
        
        pre_idx++;
        
        root.left = helper(in_left, index - 1);
        root.right = helper(index + 1, in_right);
        
        return root;
    }
}

// faster than 99.26% of Java