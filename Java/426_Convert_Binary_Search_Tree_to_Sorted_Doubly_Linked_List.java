// 解法：In-order 递归
// Time: O(N)
class Solution {
    Node pre = null;
    Node head = null;    // 记录最左结点

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inorder(root);
        pre.right = head;
        head.left = pre;
        return head;
    }

    public void inorder(Node node){
        if (node == null) return;
        inorder(node.left);     // 对左子结点调用递归，这样会先一直递归到最左结点
        if (head != null){      // 和 pre 相互连接上，然后 pre 赋值为当前结点 node
            pre.right = node;
            node.left = pre;
            pre = node;
        }else{                // 当前就是最左结点，赋值给 head 和 pre
            pre = node;
            head = node;
        }
        inorder(node.right);
    }
}
//Runtime: 0 ms, faster than 100.00% of Java