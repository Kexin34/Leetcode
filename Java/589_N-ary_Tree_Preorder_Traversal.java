// 递归

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<Integer>();
        DFS(root, result);
        return result;
    }
    
    public void DFS(Node node, List<Integer> result){
        if (node == null) return;
        result.add(node.val);
        for (Node child : node.children){
            DFS(child, result);
        }
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
