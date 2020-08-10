class Solution {
    int f[];        // n*n个格子，每个里面有4个分区
    int count, n;
    
    public int regionsBySlashes(String[] grid) {
        n = grid.length;
        f = new int[n * n * 4];
        count = n * n * 4; //初始为最大数，union时候递减
        for (int i = 0; i < n * n * 4; i++)
            f[i] = i;
        
        for (int i = 0; i < n; i++){    // 每一行
            for (int j = 0; j < n; j++){    //一行中的每一个
                //消除相邻cell中间的间隙，分别是上下，左右相邻
                if (i > 0) union(partInCell(i - 1, j, 2), partInCell(i, j, 0));
                if (j > 0) union(partInCell(i, j - 1, 1), partInCell(i, j, 3));
                
                // 处理cell内连接
                // 如果不是/，连接上+右，下+左
                if (grid[i].charAt(j) != '/'){
                    union(partInCell(i, j, 0), partInCell(i, j, 1));
                    union(partInCell(i, j, 2), partInCell(i, j, 3));
                }
                // 如果不是\，连接左+上，右+下
                if (grid[i].charAt(j) != '\\'){
                    union(partInCell(i, j, 0), partInCell(i, j, 3));
                    union(partInCell(i, j, 2), partInCell(i, j, 1));
                }
            }
        }
        return count;
    }
    
    // 标准Union Find函数，把两个结合起来union
    public void union(int x, int y){
        x = find(x);
        y = find(y);
        if (x != y){
            f[x] = y;
            count--;
        }
    }
    // 标准Union Find函数,Find找到本node的根
    public int find(int x){
        if (x != f[x])
            f[x] = find(f[x]);
        return f[x];
    }
    
    // part location number in cell 
    // k: top: 0, right: 1, bottom: 2, left: 3.
    public int partInCell(int i, int j, int k){
        int cell = i * n + j;
        int cellPart = cell * 4 + k;
        return cellPart;
    }
}
//faster than 93.54% of Java
