class Solution {
    Set<Integer> set = new HashSet<>();
    List<TreeNode> res;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        set = new HashSet<>();
        res = new ArrayList<>();
        // 把待删除值放入set，方便后续查找
        for (int i : to_delete)
            set.add(i);
        
        TreeNode r = delete(root);

        // 防止root丢失
        if (r != null) res.add(r);
        return res;
    }
    
    public TreeNode delete(TreeNode node){
        // 后序遍历，先处理左右子树，再判断根节点
        if (node == null) return null;
        
        if (node.left != null)
            node.left = delete(node.left);
        if (node.right != null)
            node.right = delete(node.right);
        
        if (!set.contains(node.val))    //本node不需要被删除，直接返回
            return node;
        
        //说明本根节点要被删除，则两个子树变成森林(放入res），返回null给父节点
        if (node.left != null) res.add(node.left);
        if (node.right != null) res.add(node.right);
        return null;
    }
}
// faster than 63.09% of Java
