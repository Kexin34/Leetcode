// 双递归
// 1.一直拿t和大的S相比，如果S的node还不等于t root value，继续递归s的左和右节点跟t比较
// 2.一旦两个tree开始有节点相同，进入下一个递归函数进行node和node对比

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isIndentical(s, t) || isSubtree(s.left, t) ||  isSubtree(s.right, t);
    }
    
    public boolean isIndentical(TreeNode s, TreeNode t) {
        if (t == null && s == null) //两个同时到达最后，说明一样
            return true;
        if (t == null || s == null) // 一个没了一个还有，说明错
            return false;
        if (s.val != t.val) 
            return false;
        // 说明两树都没有到达最后，且目前都一致，继续向下递归检查
        return isIndentical(s.left, t.left) && isIndentical(s.right, t.right);
    }
}
// faster than 95.71% of Java