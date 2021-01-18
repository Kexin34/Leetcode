// 树结构
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 前序递归
public class PreOrderRecursive{
	public void preOrder(TreeNode root){
		if (root == null) return;

		// 在递归之前写操作
		System.out.println(root.val);
		preOrder(root.left);
		preOrder(root.right);
	}
}

// 中序递归
public class InOrderRecursive{
	public void inOrder(TreeNode root){
		if (root == null) return;

		inOrder(root.left);
		// 在递归中间写操作
        System.out.println(root.val);
        inOrder(root.right);
	}
}

// 后序递归
public class PostOrderRecursive{
	public void postOrder(TreeNode root){
		if (root == null) return;

		postOrder(root.left);
		postOrder(root.right);
		// 在递归后面写操作
        System.out.println(root.val);
	}
}


//前序非递归
//用Deque模拟栈，因为Stack类是遗留类，不推荐使用。所有需要使用栈的地方都用Deque来模拟。
class Solution{
	public List<Integer> preOrderTraversal(TreeNode root){
		if (root == null) return LinkedList<>();

		List<Integer> res = new LinkedList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addFirst(root);

		while (!queue.isEmpty()){
			TreeNode node = queue.removeFirst();
			rea.add(node.val);
			if (node.right != null)
				queue.addFirst(node.right);
			if (node.left != nill)
				queue.addFirst(node.left);
		}
		return res;
	}
}

// 中序非递归
// 中序非递归遍历: 是先遍历最左节点，然后层层向上回溯。
class Solution{
	public List<Integer> inOrderTraversal(TreeNode root){
		if (root == null) return new LinkedList<>();
		List<Integer> res = new LinkedList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		TreeNode cur = root;

		while (cur != null || !queue.isEmpty()){
			while (cur != null){
				queue.addLast(cur);
				cur = cur.left;
			}
			cur = queue.removeLast();
			res.add(cur.val);
			cur = cur.right;
		}
		return res;
	}
}

// 后序非递归
// 后序非递归遍历，跟前序非递归有些类似，不过后序是先访问跟节点，然后左子节点，再访问右子节点，依次压入栈； 
// 从栈里取出时，是后序遍历的反序，需要翻转。这里利用Deque的性质，可每次插入到链头，相当于翻转。
// 和pre-order queue代码只有一句不同，就是把node val插在res开头

class Solution{
	public List<Integer> postrderTraversal(TreeNode root){
		if (root == null) return new LinkedList<>();
		LinkedList<Integer> res = new LinkedList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addFirst(root);

		while (!queue.isEmpty()){
			TreeNode node = queue.removeFirst();
			res.addFirst(node.val);
			if (node.left != null)
				queue.addFirst(node.left);
			if (node.right != null)
				queue.addFirst(node.right);
		}
		return res;
	}
}


//////////////*********///////////////////////////////////////////////////

// DFS 深度搜索-从上到下
public class Solution{
	public List<Integer> dfsUpToDown(TreeNode root){
		List<Integer> res = new LinkedList<>();
		dfs(root, res);
		return res;
	}

	public void dfs(TreeNode node, List<Integer> res){
		if (node == null) return;

		res.add(node.val);
		dfs(node.left, res);
		dfs(node.right, res);
	}
}


// DFS 深度搜索-从下向上（分治法）
public class Solution{
	public List<Integer> preOrderTraversal(TreeNode root){
		return divideAndConquer(root);
	}
	public List<Integer> divideAndConquer(TreeNode node){
		List<Integer> res = new LinkedList<>();
		if (node == null) 
			return null;

		// 分治
		List<Integer> left = divideAndConquer(node.left);
		List<Integer> right = divideAndConquer(node.right);

		// 合并结果
		res.add(node.val);
		if (left != null) res.addAll(left);
		if (right != null) res.addAll(right);
		return res;
	}
}

// 注意点：
// DFS 深度搜索（从上到下） 和分治法区别：
// 前者一般将最终结果通过指针参数传入，后者一般递归返回结果最后合并



// BFS 层次遍历
public class Solution {
    public List<Integer> levelOrder(TreeNode root) {
    	List<Integer> res = new LinkedList<>();
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);

    	while (!queue.isEmpty()){
    		// size记录当前层有多少元素（遍历当前层，再添加下一层）
    		int size = queue.size();
    		for (int i = 0; i < size; i++){
    			TreeNode node = queue.poll();
    			res.add(node.val);
    			if (node.left != null) 
    				queue.add(node.left);
    			if (node.right != null)
    				queue.add(node.right);
    		}
    	}
    	return res;
    }
}




