// Recursion
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    
    public boolean isSymmetric(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null) return true;//同时递归到末尾了
        if (t1 == null || t2 == null) return false;//其中一个树到末尾了
        
        // 向下递归，两树的左右子树反过来对比时候一样（对称）
        return (t1.val == t2.val) 
                && isSymmetric(t1.left, t2.right) 
                && isSymmetric(t1.right, t2.left);
    }
}
//  faster than 100.00% of Java


// Interation
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue <TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        
        while (!queue.isEmpty()){
            //每次提取两个结点并比较它们的值
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            //当队列为空时，或者检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
            if (t1 == null && t2 == null) continue;//抵达叶节点，跳过下面push子节点操作
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            
            //将两个结点的左右子结点按相反的顺序插入队列中。
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }
}
// faster than 44.73% of Java