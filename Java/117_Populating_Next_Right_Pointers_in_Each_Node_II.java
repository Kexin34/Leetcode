// 正规解法（常数空间）
class Solution {
    public Node connect(Node root) {
        Node dummy = new Node(0);
        Node cur = dummy;
        Node head = root;
        while (root != null){
            if (root.left != null){
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null){
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next;
            if (root == null){
                cur = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }
        return head;
    }
}
// faster than 100.00% of Java
//  O(1) space and O(n) Time complexity





// Non-recursion, more than constant space(非常数空间)
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
// faster than 62.57% of Java