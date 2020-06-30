// Greedy DFS
//强烈推荐阅读https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS

class Solution {
    int camera = 0;
    public int minCameraCover(TreeNode root) {
        int below = dfs(root);
        if (below < 1)   //if it has a child, which is leaf, then it needs camera.
            return 1 + camera;
        else    //if it has a child, which is the parent of a leaf, then it's covered.
            return camera;
    }
    
    private int dfs(TreeNode root){
        if (root == null) return 2; //上面call 她的那个就是leaf
        int left = dfs(root.left);
        int right = dfs(root.right);
        
        //if it has a child, which is leaf, then it needs camera.
        if (left == 0 || right == 0){
            camera++;
            return 1;   
        }
        /* Return 0 if it's a leaf.
         * Return 1 if it's a parent of a leaf, with a camera on this node.
         * Return 2 if it's coverd, without a camera on this node.
         */
        if (left == 1 || right == 1)
            return 2;   //其中一个子node有camera
        else 
            return 0;   //自己是leaf（左右返回的都是2）
    }
}

// faster than 100.00% of Java
