// BFS + 邻接表
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjacency = new List[numCourses];
        int[] res = new int[numCourses];
        int[] in = new int[numCourses];
        int visited = 0;
        
        /* 建造邻接表作为DAG */
        for(int i = 0; i < numCourses; i++){
            adjacency[i] = new ArrayList<>();
        }
        for(int[] cp: prerequisites){
            adjacency[cp[1]].add(cp[0]);//edge构建邻接表
            in[cp[0]]++; //被指向顶点入度表增加
        }
        
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)//找到最初起始点，放入队列
            if (in[i] == 0) queue.offer(i);
        
        // BFS处理
        while (!queue.isEmpty()){
            int from = queue.poll();
            res[visited++] = from;
            // 所有被from指向的点的入度都减一，如果有空入度的，放入队列
            for (int to : adjacency[from]){
                in[to]--;
                if (in[to] == 0)
                    queue.offer(to);
            }
        }
        // 最终若有向图中有环，则结果中元素的个数不等于总课程数
        if (visited == numCourses)
            return res;
        else
            return new int[0]; 
    }
}
// faster than 96.58% of Java







