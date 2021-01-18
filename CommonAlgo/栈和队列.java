/*总结
	熟悉栈的使用场景
	后出先出，保存临时值
	利用栈 DFS 深度搜索
	熟悉队列的使用场景
	利用队列 BFS 广度搜索
*/


// Stack 改用 Deque 的问题:
// https://mp.weixin.qq.com/s/Ba8jrULf8NJbENK6WGrVWg
Deque<Integer> stack = new ArrayDeque<>();
Deque<Integer> in = new LinkedList<>();
stack.removeLast();
stack.peekLast();
stack.isEmpty();
stack.addLast(x);
stack.addFirst(x); // stack.push(x)


////////////////Stack 栈//////////////////

// 155. Min Stack
// 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
// 思路：用两个栈实现，一个最小栈始终保证最小值在顶部
class MinStack {
    private Deque<Integer> stack;       // 数据栈
    private Deque<Integer> minStack;    // 辅助栈
    private int min;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        // 数据栈和辅助栈一定会添加元素, min更新，辅助栈添加新min
        stack.offerFirst(x);
        min = Math.min(min, x);
        minStack.offerFirst(min);
    }
    
    public void pop() {
        // 两个栈都要pop，更新pop后min值
        stack.pollFirst();
        minStack.pollFirst();
        min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peekFirst();
    }
    
    public int top() {
        // 常规检查stack peek元素
        return stack.peekFirst();
    }
    
    public int getMin() {
        return minStack.peekFirst();
    }
}


// 150. Evaluate Reverse Polish Notation
// 波兰表达式计算 > 输入: ["2", "1", "+", "3", "*"] > 输出: 9 解释: ((2 + 1) * 3) = 9
// 思路：用数组保存原来的元素，遇到+-*/取出运算，再存入结果，重复这个过程
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) return Integer.parseInt(tokens[0]);
        
        Stack<Integer> stack = new Stack<>();
        
        for (String s : tokens) {
            // 遇到数字则压入栈中
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/"))
                stack.push(Integer.valueOf(s));
            else{	// 遇到符号，则把栈顶的两个数字拿出来运算，把结果再压入栈中
                int right = stack.pop();
                int left = stack.pop();
                if (s.equals("+")) stack.push(left + right);
                if (s.equals("-")) stack.push(left - right);
                if (s.equals("*")) stack.push(left * right);
                if (s.equals("/")) stack.push(left / right);
            }
        }
        return stack.pop();
    }
}


// 394. Decode String
// 给定一个经过编码的字符串，返回它解码后的字符串。 s = "3[a]2[bc]", 返回 "aaabcbc". s = "3[a2[c]]", 返回 "accaccacc". s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
// 思路：数字一个栈，字母一个栈，取出来计算就可以了。
class Solution {
    public String decodeString(String s) {
        StringBuilder cur = new StringBuilder();
        Stack<Integer> intStack = new Stack();
        Stack<StringBuilder> strStack = new Stack();
        int k = 0;
        
        for (char ch : s.toCharArray()){
            if (Character.isDigit(ch))
                // ch是数字，k是之后[]里面要重复次数
                k = k * 10 + ch - '0';      // -'0' converting int from character.
            else if (ch == '['){
                intStack.push(k);
                // 把之前组装好的压入,cur将重新组装新的[]
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0; // reset
            }
            else if (ch == ']'){
                // 此时cur储存着最新的单个[] string, 把这个string放入tmp, 
                // cur变回原来建好的string，一个个往后面append
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k)
                    cur.append(tmp);
            }
            else
                cur.append(ch);
        }
        return cur.toString();
    }
}





////////////////////////利用栈进行 BFS 非递归搜索模板////////////////////////
// 伪代码
public class Solution{
    boolean BFS(Node root, int target) {
        Set<Node> visited;
        Stack<Node> stack;
        add root to stack;

        while (stack is not empty) {
            Node cur = the top element in stack;
            return true if cur is target;
            for (Node next : the neighbors of cur) {
                if (next is not in visited) {
                    add next to stack;
                    add next to visited;
                }
            }
            remove cur from stack; 	//这一步容易忘记
        }
        return false;
    }
}


// 94. Binary Tree Inorder Traversal
// 给定一个二叉树，返回它的中序遍历。
// 思路：通过stack 保存已经访问的元素，用于原路返回
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        
        while (!stack.isEmpty() || cur != null){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right;
        }
        return res;
    }
}



// 133. Clone Graph
// 给你无向连通图中一个节点的引用，请你返回该图的深拷贝（克隆）
class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        Queue<Node> queue = new LinkedList<Node>();
        Map<Integer, Node> map = new HashMap<>();  // <原node's value, 对应新node>
        Set<Integer> visited = new HashSet<>();

        // 1. 首先对图进行一个 BFS，把所有节点 new 出来，不处理 neighbors ，并且把所有的节点存到 map 中
        queue.offer(node);
        visited.add(node.val);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //生成每一个copy的节点(deep copy)
            Node copied = new Node(cur.val);
            copied.neighbors = new ArrayList<>();
            map.put(cur.val, copied);
            
            /* BFS会把现节点(未访问过的）邻居全部压入queue中,用于之后dequeue遍历 */
            for (Node neighbor : cur.neighbors){
                if (visited.contains(neighbor.val)) continue;
                queue.offer(neighbor);
                visited.add(neighbor.val);
            }
        }
        
        // 2. 然后再对图做一个 BFS，因为此时所有的节点已经创建了，只需要更新所有节点的 neighbors
        queue = new LinkedList<Node>();
        visited = new HashSet<>();
        queue.offer(node);
        visited.add(node.val);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            /* 更新新节点的neightbors */
            for (Node neighbor : cur.neighbors){
                map.get(cur.val).neighbors.add(map.get(neighbor.val));

                /* 把原节点邻居全部压入queue中之后遍历 */
                if (visited.contains(neighbor.val)) continue;
                queue.offer(neighbor);
                visited.add(neighbor.val);
            }
        }
        return map.get(node.val);
    }
}


// 84. Largest Rectangle in Histogram





///////////////////////Queue 队列/////////////////////////////////
// 常用于 BFS 宽度优先搜索

// 232. Implement Queue using Stacks
// 使用栈实现队列
class MyQueue {
    private Deque<Integer> in;
    private Deque<Integer> out;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        in.addFirst(x);
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        in2out();
        return out.removeFirst();
    }
    /** Get the front element. */
    public int peek() {
        in2out();
        return out.peekFirst();
    }
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
    /* 重点：把in_stack元素转移到out_stack中
    /* if out stack is not empty, pop from the out stack
    /* if out stack is empty, transmit in to out, then pop out
    */
    private void in2out() {
        if (out.isEmpty()){
            while(!in.isEmpty())
                out.addFirst(in.removeFirst());
        }
    }
}



// 102. Binary Tree Level Order Traversal
// 二叉树层次遍历
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(list);
        }
        return res;
    }
}



// 542. 01 Matrix
// 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。 两个相邻元素间的距离为 1
// 1. BFS
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0)
                    queue.offer(new int[]{i, j});
                else
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        while (!queue.isEmpty()){
            int[] cell = queue.poll();
            for (int[] dir : directions){// 遍历四个方向邻居
                int r = cell[0] + dir[0];
                int c = cell[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n ||
                   matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) continue;
                // 新邻居value（ifinity）比本cell大，说明新邻居是1/有1相邻，
                // 因为本node（很有可能）是0，所以要更新新邻居为本cell value + 1(距离)
                queue.add(new int[] {r, c}); 
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        return matrix;
    }
}
// 2. DP最优解
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dist = new int[m][n];
        int MAX_TEMP = Integer.MAX_VALUE / 2;   //除以2防止溢出
        
        // 1. DP array初始化
        // 如果 (i, j) 的元素为 0，那么距离为 0，否则设置成一个很大的数 MAX_TEMP
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == 0)
                    dist[i][j] = 0;
                else 
                    dist[i][j] = MAX_TEMP;
            }
        }
        // 2. 水平向右移动 和 竖直向下移动
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i - 1 >= 0)         // 根据upper cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                if (j - 1 >= 0)         // 根据左边的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
            }
        }
        // 3. 水平向左移动 和 竖直向上移动
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                if (i + 1 < m)          // 根据下面的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                if (j + 1 < n)          // 根据右边的cell计算
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
            }
        }
        return dist;
    }
}

































