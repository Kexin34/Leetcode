// Recursive Solution

class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int height = height(root, 1);
        int len = (int)Math.pow(2, height) - 1; //宽度
        List<List<String>> result = new ArrayList<>();
        // 构造每一层结构，里面暂时填充“”
        for (int i = 0; i < height; i++){
            List<String> tempList = new ArrayList<>();
            // 本层每一个元素位置都先被“”占位
            for (int j = 0; j < len; j++)
                tempList.add("");
            result.add(new ArrayList(tempList));
        }
        
        // 开始通过tree往2D array里面填充node value
        setTree(result, root, 0, len - 1, height, 0);
        return result;
    }
    
    // 找出一棵树的最大高度
    private int height(TreeNode root, int level){
        if (root == null) return level - 1;
        return Math.max(height(root.left, level + 1), height(root.right, level + 1));
    }
    
    // 树做递归，把node value放入array中
    private void setTree(List<List<String>> result, TreeNode root, int left,
                        int right, int height, int level){
        if (height == level || root == null) return;
        int mid = left + (right - left) / 2;
        // 把这一层中间位设为root value
        result.get(level).set(mid, String.valueOf(root.val));
        // 递归左右子树
        setTree(result, root.left, left, mid - 1, height, level + 1);
        setTree(result, root.right, mid + 1, right, height, level + 1);
    }
}
    
 //   faster than 99.54% of Java