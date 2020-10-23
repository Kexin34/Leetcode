public class Codec {

    // Encodes a tree to a single string.BFS层次遍历来序列化
    public String serialize(TreeNode root) {
        if (root == null) return "";
        // BFS层次遍历
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> res = new LinkedList<Integer>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                res.add(curNode.val);
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else
                res.add(null);
        }
        
        //去掉末尾的 null
        while (true) {
            if (res.get(res.size() - 1) == null)
                res.remove(res.size() - 1);
            else 
                break;
        }
        return res.toString();
    }
    
     

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        
        //先将字符串string还原为数组string array，再转化成int array
        // 不包括0因为data[0] = “[”，data最后位是]
        String[] preStr = data.substring(1, data.length() - 1).split(",");
        Integer[] bfsOrder = new Integer[preStr.length];
        for (int i = 0; i < preStr.length; i++) {
            if (preStr[i].trim().equals("null"))    // trim掉逗号
                bfsOrder[i] = null;
            else 
                bfsOrder[i] = Integer.parseInt(preStr[i].trim());
        }
        
        //通过BFS来层次遍历 -> 还原二叉树
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(bfsOrder[0]);
        int cur = 1;                //通过 cur index指针依次给节点赋值
        queue.offer(root);
        while(!queue.isEmpty()){
            if (cur == bfsOrder.length) break;
            
            TreeNode curNode = queue.poll();
            if(bfsOrder[cur] != null){//说明本node有左子node
                curNode.left = new TreeNode(bfsOrder[cur]);
                queue.add(curNode.left);
            }
            cur++;
            
            if (cur == bfsOrder.length) break;
            
            if(bfsOrder[cur] != null){//说明本node有右子node
                curNode.right = new TreeNode(bfsOrder[cur]);
                queue.add(curNode.right);
            }
            cur++;
        }
        return root;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));