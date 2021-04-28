class Vector2D {
    int[][] v;
    int i = 0, j = 0;
    
    public Vector2D(int[][] vec) {
        this.v = vec;
    }
    
    public int next() {
        if (hasNext())
            return v[i][j++];
        else 
            return -1;
    }
    
    public boolean hasNext() {
        // Move to next available vector, 用while防止有[]空数组
        while (i < v.length && j == v[i].length){
            i++;
            j = 0;
        }
        return i < v.length;
    }
}
// Runtime: 9 ms, faster than 99.71% of Java
