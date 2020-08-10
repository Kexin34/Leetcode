class Solution {
    public int findJudge(int N, int[][] trust) {
        int[] degree = new int[N + 1];
        for (int[] entry : trust){
            degree[entry[0]]--;
            degree[entry[1]]++;
        }
        // 遍历所有节点，检查degree
        // node start with 1
        for (int i = 1; i <= N; i++){
            if (degree[i] == N - 1)
                return i;
        }
        return -1;
    }
}
// Time complexity: O(N+T)
// Space complexity: O(N)