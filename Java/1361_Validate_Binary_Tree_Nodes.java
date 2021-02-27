// ## Basic Ideas: union find + indegree 
// ##
// ##    Only one node with the indegree as 0
// ##    For all other nodes, the indegree should be 1
// ##
// ## Complexity:
// Time complexity: O(n)
// Space complexity: O(n)



class Solution {
    int[] parent;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] in = new int[n];
        parent = new int[n];
        // union parent数组建立
        for (int i = 0; i < n; i++)
            parent[i] = i;
        
        //对于每个节点的左右子节点，如果存在，入度+1再检查，然后和本节点进行union
        for (int i = 0; i < n; i++){
            int left = leftChild[i];
            int right = rightChild[i];
            if (left != -1){
                in[left]++;
                if (in[left] > 1) return false;
                union(i, left);
            }
            if (right != -1){
                in[right]++;
                if (in[right] > 1) return false;
                union(i, right);
            }
        }
        int zeroCnt = 0;
        // 计算入度为0的节点个数，如果不是1则错误（只有root为0）
        for (int i = 0; i < n; i++)
            if (in[i] == 0) zeroCnt++;
        if (zeroCnt != 1) return false;
        // 找出parent指向自己的节点个数，如果不是1也错（只有root parent是自己）
        zeroCnt = 0;
        for (int i = 0; i < n; i++)
            if (parent[i] == i) zeroCnt++;
        
        return zeroCnt == 1;
    }
   
                    
    public int find(int x){
        while (x != parent[x])
            x = parent[x];
        return x;
    }
    public void union(int x, int y) {
        int x2 = find(x);
        int y2 = find(y);
        parent[y2] = x2;
    }
}
// 2 ms, faster than 98.87% of Java
