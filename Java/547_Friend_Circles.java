//题目描述：好友关系可以看成是一个无向图，例如第 0 个人与第 1 个人是好友
// 那么 M[0][1] 和 M[1][0] 的值都为 1。
//DFS
class Solution {
    int n;
    public int findCircleNum(int[][] M) {
        n = M.length;
        int circleNum = 0;
        boolean[] visited = new boolean[n];
        

        for (int i = 0; i < n; i++){
            if (!visited[i]){       // 把有1的area都找出来, 再去对于没有遍历到的人在找其朋友圈的人
                dfs(M, i, visited);
                circleNum++;
            }
        }
        return circleNum;
    }
    
    public void dfs(int[][] M, int i, boolean[] visited){
        if (i < 0 || i >= n || visited[i] == true) return;
        visited[i] = true;
        // 遍历这个人的好友
        for (int k = 0; k < n; k++){
            if (M[i][k] == 1 && !visited[k])
                dfs(M, k, visited);
        }
    }
}
// faster than 100.00% of Java