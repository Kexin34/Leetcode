// BFS - 2d array
// faster than 47.38% of Java
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>(); // int[] = i, j location
        int count_fresh = 0;
        
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (grid[i][j] == 2)
                    queue.offer(new int[]{i, j});
                else if (grid[i][j] == 1)
                    count_fresh++;
            }
        }
        //if count of fresh oranges is zero --> return 0 
        if (count_fresh == 0) return 0;
        
        int time = 0; // number of minutes that must elapse
        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()){
            time++;    // each layer propogation, time elapse
            int size = queue.size(); //current level
            for (int i = 0; i < size; i++){
                int[] point = queue.poll();
                // propogate in four direction (put into queue)
                for (int dir[] : dirs){     
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    // if x or y is out of bound
                    // or the orange at (x , y) is already rotten
                    // or the cell at (x , y) is empty
                    // we do nothing
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 ||
                       grid[x][y] == 2) 
                        continue;
                    // mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    //put the new rotten orange at (x , y) in queue
                    queue.offer(new int[]{x, y});
                    //decrease the count of fresh oranges by 1
                    count_fresh--;
                }
            }
        }
        return count_fresh == 0 ? time - 1 : -1; 
    }
}






// BFS优化 queue存的是一维， 更好理解,， 本质一样
// faster than 47.38% of Java 
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new LinkedList<>(); 
        
        int count_fresh = 0;
        
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (grid[i][j] == 2)
                    queue.offer(i * cols + j);
                else if (grid[i][j] == 1)
                    count_fresh++;
            }
        }
        //if count of fresh oranges is zero --> return 0 
        if (count_fresh == 0) return 0;
        
        int time = 0; // number of minutes that must elapse
        //bfs starting from initially rotten oranges
        while (!queue.isEmpty()){
            if (count_fresh > 0){
                time++;
                int size = queue.size(); 
                for (int i = 0; i < size; i++){
                    int cur = queue.poll();
                    int x = cur / cols;
                    int y = cur % cols;
                    for (int[] dir : dirs){
                        int nextX = x + dir[0];
                        int nextY = y + dir[1];
                        if (hasFreshOrange(grid, nextX, nextY)){
                            //put the new rotten orange in queue
                            queue.offer(nextX * cols + nextY);
                            //decrease the count of fresh oranges by 1
                            count_fresh--;
                        }
                    }
                }
            }
            if (count_fresh == 0)
                break;
        }
        return count_fresh == 0 ? time : -1; 
    }
    
    private boolean hasFreshOrange(int[][] grid, int r, int c){
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1){
            grid[r][c] = 2;
            return true;
        }
        return false;
    }
}



