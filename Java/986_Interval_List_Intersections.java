// 解法：https://github.com/labuladong/fucking-algorithm/blob/master/%E7%AE%97%E6%B3%95%E6%80%9D%E7%BB%B4%E7%B3%BB%E5%88%97/%E5%8C%BA%E9%97%B4%E4%BA%A4%E9%9B%86%E9%97%AE%E9%A2%98.md

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0)
            return new int[][]{};
        int i = 0, j = 0;  //双指针
        List<int[]> res = new ArrayList<>();
        while(i < A.length && j < B.length){
            int a1 = A[i][0],  a2 = A[i][1];
            int b1 = B[j][0], b2 = B[j][1];

            //“相交”只有四种情况，只需要满足两个条件
            if (b2 >= a1 && a2 >= b1)
                res.add(new int[]{Math.max(a1,b1), Math.min(a2, b2)});//计算出交集
            
            // i对应区间已经用完，移动指针
            if (b2 < a2)
                j += 1;
            else
                i += 1;
        }
        return res.toArray(new int[res.size()][2]);
    }
}

// faster than 78.14% of Java