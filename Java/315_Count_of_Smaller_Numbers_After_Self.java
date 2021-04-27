// Binary Indexed Tree (FenwickTree)
// Time complexity: O(nlogn)
// Space complexity: O(k), k is the number unique elements
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        
        // 构建rank，大小是unique元素个数+1，<元素value，它的rank> 
        Map<Integer, Integer> ranks = new HashMap<>(); 
        int rank = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1])
                ranks.put(sorted[i], ++rank);
        }
        
        // 构建FenwickTree，变成频率的和(它的freq次数)
        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<>();
        // 	2. 倒叙输入来转换成前置的prefix sum(频率和)
        for (int i = nums.length - 1; i >= 0; i--) {
            // 找出nums[i]对应的rank出现的次数
            int sum = tree.query(ranks.get(nums[i]) - 1);
            ans.add(sum);
            tree.update(ranks.get(nums[i]), 1);
        }
        Collections.reverse(ans);
        return ans;
    }
    
    class FenwickTree {    
        private int[] sums;    

        public FenwickTree(int n) {
          sums = new int[n + 1];
        }

        public void update(int i, int delta) {    
          while (i < sums.length) {
              sums[i] += delta;
              i += lowbit(i);
          }
        }

        public int query(int i) {       
          int sum = 0;
          while (i > 0) {
              sum += sums[i];
              i -= lowbit(i);
          }
          return sum;
        }    
    };
    private static int lowbit(int x) { return x & (-x); }
}
// Runtime: 69 ms, faster than 65.44% of Java





// BST
class Solution {
    class Node {
        int val, count, left_count;
        Node left, right;
        public Node(int val) {
            this.val = val;
            this.count = 1;
        }
        public int less_or_equal(){
            return count + left_count;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        int n = nums.length;
        
        // 倒叙进行，变成看前面，最后再reverse
        Node root = new Node(nums[n - 1]);// 倒叙首位作为根节点
        ans.add(0);  // 根节点的左边没有比她小的数字
        for (int i = n - 2; i >= 0; i--) 
            ans.add(insert(root, nums[i]));
        
        Collections.reverse(ans);
        return ans;
    }
    // Returns the number of elements smaller than val under root.
    private int insert(Node root, int val) {
        if (root.val == val) {
            root.count++;
            return root.left_count;
        }else if (val < root.val) {  // 左子节点
            root.left_count++;
            if (root.left == null) {
                root.left = new Node(val);
                return 0;
            }
            return insert(root.left, val);// 递归
        } else {
            if (root.right == null) {
                root.right = new Node(val);
                return root.less_or_equal();
            }
            return root.less_or_equal() + insert(root.right, val);
        }
    }
}
