// BFS, (如果没有要求空间复杂度)
class Solution {
    public Node connect(Node root) {
        if (root == null) return root;
        
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        // iterates over each level one by one
        while(!queue.isEmpty()){
            int size = queue.size();
            Node pre = null;
            // iterates over all the nodes on the particular level
            for(int i = 0; i < size; i++){
                Node cur = queue.poll();
                //从第二个节点开始，将前一个节点的 pre 指向当前节点
                if (i > 0)
                    pre.next = cur;
                pre = cur;
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
            }
        }
        return root;
    }
}
//faster than 53.14% of Java


// 迭代
class Solution {
    public Node connect(Node root) {
       if (root == null) return root;
        
        Node leftmost = root;
        while(leftmost.left != null){
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                
                // CONNECTION 2
                if (head.next != null) 
                    head.right.next = head.next.left;
                
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            // Move onto the next level
            leftmost = leftmost.left;
        }
        return root;
    }
}
// faster than 100.00% of Java