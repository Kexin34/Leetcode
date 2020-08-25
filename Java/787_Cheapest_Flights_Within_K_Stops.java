// DFS
class Solution {
    Map<Integer, List<int[]>> map= new HashMap<>();
    int ans;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // create graph first. hashmap key:node src, value :int[dst, cost]
        for(int[] i:flights){
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        ans = Integer.MAX_VALUE;

        dfs(src, dst, K + 1, 0);    // current cost = 0, total k + 1 steps
        return ans == Integer.MAX_VALUE? -1 : ans;
    }
    
    public void dfs(int cur, int dst, int k, int cost){
        if (k < 0) return; // 中转机会用完了，说明不行
        if (cur == dst){    // 到达终点，成功
            ans = cost;
            return;
        }
        if(!map.containsKey(cur))return; // ？？？？？？？？？？
        
        for (int[] value : map.get(cur)){   // 遍历现节点的所有dest
            if (cost + value[1] > ans) continue;    // 终点：剪枝！
            dfs(value[0], dst, k - 1, cost + value[1]);
        }
    }
}
// faster than 10.07% of Java



// 解法二： BFS
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map= new HashMap<>();
        // create graph first. hashmap key:node src, value :int[dst, cost]
        for(int[] i:flights){
            map.putIfAbsent(i[0],new ArrayList<>());
            map.get(i[0]).add(new int[]{i[1],i[2]});
        }
        // queue存的是[node, src到这个node的距离]
        Queue<int[]> queue = new LinkedList<>();
        int ans = Integer.MAX_VALUE;
        int steps = 0;
        queue.add(new int[]{src, 0});
        
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] cur = queue.remove();
                
                if (cur[0] == dst)      // 找到一个合法路径，看是否小于目前最短路径
                    ans = Math.min(ans, cur[1]);
                
                if(!map.containsKey(cur[0])) continue;
                
                for (int[] value : map.get(cur[0])){   // 遍历所有连接的dst，合法的话加入queue
                    if (cur[1] + value[1] > ans) continue;    // 剪枝
                    queue.add(new int[] {value[0], cur[1] + value[1]});
                }
            }
            if (steps++ > K) break;
        }
        return ans == Integer.MAX_VALUE? -1 : ans;
    }
}
// faster than 82.89% of Java



// 解法三(最优解）：Bellman-Ford 算法，用了滚动数组
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] cost = new int[n];    //   简化成1D- dp array
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        
        for (int i = 0; i <= K; i++){
            int[] temp = Arrays.copyOf(cost, n); // 滚动数组，拷贝这一层
            // cost相当于走i-1步的，temp相当于走i步的

            for(int[] f: flights){
                int curr = f[0], next = f[1], price = f[2];
                if(cost[curr] == Integer.MAX_VALUE)   continue;// src无法到达这点
                temp[next] = Math.min(temp[next], cost[curr] + price);
            }
            cost=temp;
        }
        return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
    }
}
// faster than 96.70% of Java