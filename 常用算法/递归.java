// 344. Reverse String
// 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组  char[]  的形式给出。
class Solution {
    public void reverseString(char[] s) {
        reverse(s, 0, s.length - 1);
    }
    
    private void reverse(char[] s, int left, int right){
        if (left >= right) return;

        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        reverse(s, left, right);
    }
}



// 24. Swap Nodes in Pairs
// 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
class Solution {
    public ListNode swapPairs(ListNode head) {
        return reverse(head);
    }
    
    public ListNode reverse(ListNode node){
        if (node == null || node.next == null) return node;
        ListNode newList = node.next;   // tmp，保留第二个node
        node.next = reverse(node.next.next);
        newList.next = node;
        return newList;
    }
}


// 95. Unique Binary Search Trees II
// 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) return new LinkedList<TreeNode>();
        return generateSubtrees(1, n);
    }
    
    private List<TreeNode> generateSubtrees(int start, int end){
        LinkedList<TreeNode> ans = new LinkedList<TreeNode>();
        /* based case：此时没有数字，将 null 加入结果中 */
        if(start > end){     
            ans.add(null);
            return ans;
        }
        /* 尝试每个数字作为根节点 */
        for (int i = start; i <= end; i++){ 
            //递归生成所有可能的左右子树
            List<TreeNode> leftSubtrees = generateSubtrees(start, i - 1);
            List<TreeNode> rightSubtrees = generateSubtrees (i + 1, end);
            
            //i的左子树右子树两两组合
            for (TreeNode left : leftSubtrees){
                for (TreeNode right : rightSubtrees){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    ans.add(root);//加入到最终结果中
                }
            }
        }
        return ans;
    }
}


//////////////////////////////////递归+备忘录/////////////////////////


// 509. Fibonacci Number
class Solution {
    public int fib(int N) {
        if (N <= 1) return N;
        int[] memo = new int[N + 1];
        memo[1] = 1;
        return helper(N, memo);
    }
    
    public int helper(int N, int[] memo){
        if (memo[N] > 0) return memo[N];
        if (N <= 0) return 0;
        memo[N] = helper(N - 1, memo) + helper(N - 2, memo);
        return memo[N];
    }
}













