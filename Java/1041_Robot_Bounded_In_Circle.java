class Solution {
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int dir = 0;            // facing north
        // north = 0, east = 1, south = 2, west = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        for (char c : instructions.toCharArray()){
            if (c == 'L')
                dir = (dir + 3) % 4;
            else if (c == 'R')
                dir = (dir + 1) % 4;
            else {
                x += directions[dir][0];
                y += directions[dir][1];   
            }
        }
        // trye if back to (0,0) or is not facing north
        return (x == 0 && y == 0) || dir != 0;
    }
}
// faster than 100.00% of Java
// Time complexity: O(n), Space complexity: O(1)

