// 解法1： 遍历
// 从左到右push -> 从右到左pop并放入答案，最后reverse答案
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            res.add(node.val);
            for (Node child: node.children){
                stack.push(child);
            }
        }
        Collections.reverse(res);
        return res;
    }
}
// 3 ms, faster than 28.48% of Java 


// 解法2： Postorder递归
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }
    
    public void postOrder(Node root, List<Integer> res) {
        if (root == null) return;
        for (Node child: root.children)
            postOrder(child, res);
        res.add(root.val);
    }
}
// 0 ms, faster than 100.00% of Java