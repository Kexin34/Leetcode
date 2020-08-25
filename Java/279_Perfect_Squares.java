// 最简单直接的DP解法, 记住这个！
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++){
            for (int j = 1; j * j <= i; ++j)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }
} 
// faster than 65.34% of Java
// Time complexity: O(n * sqrt(n))
// Space complexity: O(n)



// 最早看到的DP解法，基本一样，但是很啰嗦，不需要
class Solution {
    public int numSquares(int n) {
        List<Integer> squareList = generateSquaresList(n);
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            int min = Integer.MAX_VALUE;
            for(int square : squareList){
                if(square > i){
                    break;
                }
                min = Math.min(min, dp[i - square] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
    
    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,16,25,36,49,...
     */
    private List<Integer> generateSquaresList(int n){
        List<Integer> square_list = new ArrayList<>();
        int square = 1;
        int diff = 3;
        while(square <= n){
            square_list.add(square);
            square += diff;
            diff += 2;
        }
        return square_list;
    }
}
//  faster than 21.86% of Java 






// 解法三：Greedy + BFS （高效）
// 将每个整数看成图中的一个节点，如果两个整数之差为一个平方数，那么这两个整数所在的节点就有一条边。
// 要求解"最小的"平方数数量，就是求解从节点 n 到节点 0 的"最短"路径。

class Solution {
    public int numSquares(int n) {
        List<Integer> square_list = generateSquaresList(n);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(n);
        visited[n] = true;
        int step = 0;       // number of perfect square numbers 
        
        while (!queue.isEmpty()){
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++){
                int cur = queue.poll();
                for (int s : square_list){
                    int next = cur - s;
                    if (next < 0) break;            //现在就小于0，接下来的s也会是
                    if (next == 0) return step;     //found! remainder是square numbers.
                    if (!visited[next]){            //  下一个节点没有被访问过
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }
        return n; //如果没有找到一个平方数，只能是1 * n个数 = n
    }
    
    /**
     * 生成小于 n 的平方数序列
     * @return 1,4,9,16,25,36,49,...
     */
    public List<Integer> generateSquaresList(int n){
        List<Integer> square_list = new ArrayList<>();
        int square = 1;
        int diff = 3;
        // 每个序列之间都相差1，3，5，7，9....diff + 2
        while (square <= n){
            square_list.add(square);
            square += diff;
            diff += 2;
        }
        return square_list;
    }
}



