// Method 1 : Recursive
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (N == 1) 
            ans.add(new TreeNode(0));
        else if (N % 2 != 0){
            for (int i = 2; i <= N; i += 2){ //可用的头
                List<TreeNode> leftBranch = allPossibleFBT(i - 1);
                List<TreeNode> rightBranch = allPossibleFBT(N - i);
                
                for (Iterator<TreeNode> left_iter = leftBranch.iterator(); left_iter.hasNext();){
                    TreeNode left = left_iter.next();
                    for (Iterator<TreeNode> right_iter = rightBranch.iterator(); right_iter.hasNext();){
                        TreeNode right = right_iter.next();
                        TreeNode tree = new TreeNode(0);
                        
                        /* If we're using the last right branch, then this will be the last time this left branch 
                           is used and can hence be shallow copied, otherwise the tree will have to be cloned 
                        */
                        tree.left = right_iter.hasNext() ? clone(left) : left;
                        /* If we're using the last left branch, then this will be the last time this right branch 
                           is used and can hence be shallow copied, otherwise the tree will have to be cloned 
                        */
                        tree.right = left_iter.hasNext() ? clone(right) : right;
                        
                        ans.add(tree);
                    }
                }
            }
        }
        return ans;
    }
    
    private TreeNode clone(TreeNode tree){
        if (tree == null) return null;
        TreeNode newTree = new TreeNode(tree.val);
        newTree.left = clone(tree.left);
        newTree.right = clone(tree.right);
        return newTree;
    }
}
//faster than 43.82% of Java



// Method 2: Iterative
class Solution {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (N % 2 == 0)
            return ans;
        else if (N == 1){
            ans.add(new TreeNode(0));
            return ans;
        }
        // Build up a cache of a all possible FBT for the N - 2 levels
        // these levels will be linked together as a graph and should not be returned
        List<List<TreeNode>> cache = new ArrayList<List<TreeNode>>();
        cache.add(new ArrayList<TreeNode>());
        cache.get(0).add(new TreeNode(0));
        for (int root = 1; root < N / 2; root++){
            List<TreeNode> new_root = new ArrayList<TreeNode>();
            for (int left_size = 0; left_size < root; ++left_size){
                for (TreeNode left : cache.get(left_size)){
                    for (TreeNode right : cache.get(root - left_size - 1)){
                        TreeNode new_tree = new TreeNode(0);
                        new_tree.left = left;
                        new_tree.right = right;
                        new_root.add(new_tree);
                    }
                }
            }
            cache.add(new_root);
        }
        // Cached values are linked together and must be cloned to be unlinked trees before returning
        for (int root = 0; root < N / 2; ++root){
            for (TreeNode left : cache.get(root)){
                for (TreeNode right : cache.get(N / 2 - root - 1)){
                    TreeNode new_tree = new TreeNode(0);
                    new_tree.left = clone(left);
                    new_tree.right = clone(right);
                    ans.add(new_tree);
                }
            }
        }
        return ans;
    }

    private TreeNode clone(TreeNode tree){
        if (tree == null) return null;
        TreeNode newTree = new TreeNode(tree.val);
        newTree.left = clone(tree.left);
        newTree.right = clone(tree.right);
        return newTree;
    }
}

//faster than 44.26% of Java


