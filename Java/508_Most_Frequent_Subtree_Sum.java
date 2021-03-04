// 解法：递归 
// 哈希表存每个sum所对应的出现频率
// Time: O(n)
// spave: O(log(n))

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    int max_freq = -1;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> ans = new ArrayList<>();

        // 此时已经遍历完所有节点，在哈希表中找出最大频率的sum
        for (int s : map.keySet()){
            if (map.get(s) == max_freq)
                ans.add(s);
        }
        // 把list转换成array
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
    private int dfs(TreeNode root){
        if (root == null) return 0;
        int sum = root.val + dfs(root.left) + dfs(root.right);

        // 找出当前sum的频率并更新哈希表
        int cur_freq = map.getOrDefault(sum, 0) + 1;
        map.put(sum, cur_freq);

        max_freq = Math.max(max_freq, cur_freq);
        return sum;
    }
}
// faster than 51.72% of Java 

