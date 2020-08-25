// 解法：基本BFS
class Solution {
    private final int[][] DIRECTIONS = new int[][] {{2, 1}, {1, 2}, 
                {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    
    public int minKnightMoves(int x, int y) {
        // 四个象限对称，都放到第一象限来计算
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<int[]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(new int[] {0, 0});
        visited.add("0,0");
        
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.remove();
                int curX = cur[0];
                int curY = cur[1];
                
                if (curX == x && curY == y) return steps;
                
                for (int[] d : DIRECTIONS) {
                    int newX = curX + d[0];
                    int newY = curY + d[1];
                    // 把所有valid next方位都扔到queue中，并标为visited
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            steps++;    // 每层结束后都要加一
        }
        return -1;
    }
}
// faster than 29.93% of Java 