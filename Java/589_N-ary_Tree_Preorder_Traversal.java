// 递归
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    List<Integer> res;
    public List<Integer> preorder(Node root) {
        res = new ArrayList<>();
        preOrder(root);
        return res;
    }
    
    public void preOrder (Node root){
        if (root == null) return;
        res.add(root.val);
        for (Node child: root.children) {
            preOrder(child);
        }
        return;
    }
}
// 遍历
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if (node == null) continue;
            ans.add(node.val);
            // want to follow the order Left -> Right (preorder)
            // need to push right to left, thus need to revert the children
            Collections.reverse(node.children);
            for (Node child : node.children)
                stack.push(child);
        }
        return ans;
    }
}
//faster than 6.33% of Java
