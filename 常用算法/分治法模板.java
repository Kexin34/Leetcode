/* 分治法应用
 * 先分别处理局部，再合并结果

 * 适用场景:
 * 	1. 快速排序 quick sort
 * 	2. 归并排序 merge sort
 * 	3. 二叉树相关问题

 * 分治法模板 step:
 * 	1. 递归返回条件
 * 	2. 分段处理
 * 	3. 合并结果
*/

// 伪代码
public class Solution {
    public ResultType traversal(TreeNode root) {
        if (root == null) {
            // do something and return
        }

        // Divide
        ResultType left = traversal(root.left);
        ResultType right = traversal(root.right);

        // Conquer
        ResultType result = Merge from left and right

        return result;
    }
}


// 典型示例
// 通过分治法遍历二叉树
public class Solution {
    public List<Integer> prerderTraversal(TreeNode root) {
        return divideAndConquer(root);
    }

    public List<Integer> divideAndConquer(TreeNode node) {
        List<Integer> result = new LinkedList<>();
        if (node == null) {
            return null;
        }
        // 分治
        List<Integer> left = divideAndConquer(node.left);
        List<Integer> right = divideAndConquer(node.right);
        // 合并结果
        result.add(node.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}


// 归并排序
// 注意点：递归需要返回结果用于合并  
public class Solution{
	public int[] mergeSortRoot(int[] nums){
		mergeSort(nums, 0, nums.length - 1);
		return nums;
	}

	private void mergeSort(int[] nums, int l, int r){
		if (l < r){
			int mid = (l + r) / 2;
			// 分治
			mergeSort(nums, l, mid);
			mergeSort(nums, mid + 1, r);
			// 合并
			merge(nums, l, mid, r);
		}
	}

	private void merge(int[] nums, int l, int mid, int r){
		int i = l, j = mid + 1, k = l;			// k是tmp的index
		int[] tmp = new int[nums.length];

		while (i <= mid && j <= r){
			if (nums[i] > nums[j])
				tmp[k++] = nums[j++];
			else
				tmp[k++] = nums[i++];
		}
		while (i <= mid) 						//如果左/右有剩余的元素，全copy
			tmp[k++] = nums[i++];
		while (j <= r)
			tmp[k++] = nums[j++];

		for (i = l; i <= r; i++) 				// 把tmp拷贝给nums
			nums[i] = tmp[i];
	}
}




// 快速排序 
// 注意点：快排由于是原地交换所以没有合并过程 传入的索引是存在的索引（如：0、length-1 等），越界可能导致崩溃 
public class Solution {
	public int[] quickSort(int[] nums){
		// 思路：把一个数组分为左右两段，左段小于右段，类似分治法没有合并过程
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	private void quickSort(int[] nums, int start, int end){
		if (start < end){
			// pivot 是分割点，pivot左边的比nums[pivot]小，右边的比nums[pivot]大
			int pivot = partition(nums, start, end);
			quickSort(nums, 0, pivot - 1);
			quickSort(nums, pivot + 1, end);
		}
	}

	private int partition(int[] nums, int start, int end){
		int target = nums[end];
		int i = start;

		// 将小于target移到数组前面
		for (int j = start; j < end; j++){
			if (nums[j] < target){
				swap(nums, i, j);
				i++;
			}
		}
		// 把中间的值换为用于比较的基准值
        swap(nums, i, end);
        return i;
	}

	private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}



///////////////////////////////////真题////////////////////////////////

// 104. maximum-depth-of-binary-tree
// 给定一个二叉树，找出其最大深度。
// 解法：分治法
// 计算maxDepth(root.left)，maxDepth(root.right)就是分治，
// Math.max(maxDepth(root.left), maxDepth(root.right)) + 1就是合并的过程
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int maxSub = Math.max(maxDepth(root.left), maxDepth(root.right));
        return maxSub + 1;
    }
}

// 110. Balanced Binary Tree
// 给定一个二叉树，判断它是否是高度平衡的二叉树。
// 思路：分治法，左边平衡 && 右边平衡 && 左右两边高度 <= 1，遍历一遍，
// 如果有左右两边高度 > 1，则不是高度平衡的二叉树。
class Solution {
    private boolean result = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    
    private int maxDepth(TreeNode root){
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        if (Math.abs(left - right) > 1) 
            result = false;
        // 返回的相当于子树的最大高度
        return Math.max(left, right) + 1;
    }
}

// 124. Binary Tree Maximum Path Sum
// 给定一个非空二叉树，返回其最大路径和。
// 思路：分治法，递归计算出左右子节点的最大贡献值，只有在最大贡献值大于 0 时，才会选取对应子节点； 
// 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值；
// 每次递归返回该节点的最大贡献值
class Solution {
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    
    public int maxGain(TreeNode node){
        if (node == null) return 0;
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int curMaxSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, curMaxSum);
        
        // 每次递归返回该节点的最大贡献值,只能是单path，所以要挑max sub gain
        return node.val + Math.max(leftGain, rightGain);
    }
}

// 236. Lowest Common Ancestor of a Binary Tree
// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
// 思路：分治法
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	// 终止条件：为空或者找到当前是其中一个，返回当前节点
        if (root == null || root == p || root == q) return root;
        
        // 遍历左右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 若都不为空，说明p，q分别在左右子树中，当前就为最近公共祖先
        if (left != null && right != null) return root;
        // 否则返回非空子树节点（包含p或者q的子树）
        return left == null ? right : left;
    }
}





//////////////////////////////////////////BFS 层次应用//////////////////

// 102. Binary Tree Level Order Traversal
// 给一个二叉树，请返回其按层序遍历得到的节点值.（即逐层地，从左到右访问所有节点）
// 思路：用一个队列记录一层的元素，然后扫描这一层元素添加下一层元素到队列（一个数进去出来一次，所以复杂度 O(logN)）
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

// 103. Binary Tree Zigzag Level Order Traversal
// 给定一个二叉树，返回其节点值的锯齿形层次遍历。Z 字形遍历
// 奇数层翻转放入result
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int cnt = 0; // 层数
        
        while (!queue.isEmpty()){
            List<Integer> oneLevel = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 本层已经遍历完，考虑添加到res的order
            if (cnt % 2 == 1) // 如果是奇数层，翻转再放入result
                Collections.reverse(oneLevel);
            res.add(oneLevel);
            cnt++;
        }
        return res;
    }
}





//////////////////////////////////////二叉搜索树应用/////////////////////


// 98. Validate Binary Search Tree
// 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
// 思路 1：中序遍历，如果中序遍历得到的节点的值小于等于前一个 preVal，说明不是二叉搜索树
class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double preVal = - Double.MAX_VALUE;
        TreeNode node = root;
        
        while (!stack.isEmpty() || node != null){
            // 左
            while (node != null){
                stack.offerFirst(node);
                node = node.left;
            }

            node = stack.pollFirst();
            // 如果中序遍历得到的节点的值小于等于前一个 preVal，说明不是二叉搜索树
            if (node.val <= preVal) return false;
            preVal = node.val;

            // 处理右子树
            node = node.right;
        }
        return true;
    }
}
// 思路 2：递归法，判断左 MAX < 根 < 右 MIN
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return isValidBST(root, null, null);
    }
    
    public boolean isValidBST(TreeNode node, Integer min, Integer max){
        if (node == null) return true; //base case: when the node has no child node

        // compare the node value with lower/upper limit
        if ((min != null && node.val <= min) || 
            (max != null && node.val >= max))
            return false;
        else
            return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }
}



// 701. Insert into a Binary Search Tree
// 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
// 思路：找到最后一个叶子节点满足插入条件即可
// DFS查找插入位置
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 终止条件：找到插入的位置，添加新的节点并返回
        if (root == null) 
            return new TreeNode(val);
        
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        else
            root.left = insertIntoBST(root.left, val);
        return root;
    }
}
// faster than 100.00% of Java online 




/*

总结:
	掌握二叉树递归与非递归遍历
	理解 DFS 前序遍历与分治法
	理解 BFS 层次遍历
	
*/























