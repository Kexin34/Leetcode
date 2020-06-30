class Solution {
    int preIndex = 0;
    int posIndex = 0;
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex])
            root.left = constructFromPrePost(pre, post);
        if (root.val != post[posIndex])
            root.right = constructFromPrePost(pre, post);
        posIndex++;
        return root;
    }
}

//faster than 100.00% of Java

class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++)
            map.put(post[i], i);
        
        return dfs(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }
    
    private TreeNode dfs(int[] pre, int preStart, int preEnd, int[] post, int postStart, 
                        int postEnd, Map<Integer, Integer> map){
        if (preStart > preEnd || postStart > postEnd)
            return null;
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart + 1 <= preEnd){
            int deltaIndex = map.get(pre[preStart + 1]) - postStart;
            root.left = dfs(pre, preStart + 1, preStart + 1 + deltaIndex, post, postStart, 
                            postStart + deltaIndex, map);
            root.right = dfs(pre, preStart + 1 + deltaIndex + 1, preEnd, post, postStart + 
                             deltaIndex + 1, postEnd - 1, map);
        }
        return root;
    }
}

//faster than 72.44% of Java