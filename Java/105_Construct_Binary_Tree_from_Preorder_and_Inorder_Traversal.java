class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();//key:元素值 value:元素位置
        for(int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i],i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, 
                                 inorder.length - 1, inMap);
        return root;
    }
    //递归function，就是一个前序遍历
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder,
                              int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]); //root node为前序数组第一个
        
        // 计算root在中序数组的位置，这样能算出左子树有多少个node
        int inRoot = inMap.get(root.val);
        int numsOfLeft = inRoot - inStart;
        
        // 在前序数组中，右子树index就是preStart + numsOnLeft + 1
        // 左子树范围是前序数组[prestart+1 到 prestart + numsOfLeft]
                // 或者说是中序数组[开头到root前一位]
        // 右子树范围是前序数组[preStart + numsOnLeft + 1 到 最后]
                // 或说中序数组[root下一个位置到最后]
        root.left = buildTree(preorder, preStart + 1, preStart + numsOfLeft, inorder, 
                              inStart, inRoot - 1, inMap);
        root.right = buildTree(preorder, preStart + numsOfLeft + 1, preEnd, inorder, 
                              inRoot + 1, inEnd, inMap);
        return root;
    }
}

// faster than 99.26% of Java