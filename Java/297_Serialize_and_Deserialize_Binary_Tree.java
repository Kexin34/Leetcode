// 递归，前序遍历解法
public class Codec {
    String SEP = ",";
    String NULL = "#";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    // 辅助函数，将二叉树存入stringbuilder
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        /***** 前序遍历位置 *****/
        sb.append(root.val).append(SEP);
        
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // 主函数，将字符串转化为二叉树结构
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP))
            nodes.add(s);
        
        return deserialize(nodes);
    }
    
    // 辅助函数，通过nodes列表构造二叉树
    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) return null;
        
        /***** 前序遍历位置 *****/
        // 列表最左侧就是根节点
        String first = nodes.removeFirst();
        if (first.equals(NULL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));
        /*******************/
        
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}
// Runtime: 6 ms, faster than 94.20% of Java





// 层次遍历解法

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
// Runtime: 14 ms, faster than 44.74% of Java