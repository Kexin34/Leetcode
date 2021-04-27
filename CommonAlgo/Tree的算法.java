// Fenwick Tree
// Time complexity:
// init O(nlogn), n个数字，每个数字做logn的计算
// query: O(logn)
// update: O(logn)

class FenwickTree {    
    private int[] sums;    

    public FenwickTree(int n) {
      sums = new int[n + 1];
    }

    public void update(int i, int delta) {    
      while (i < sums.length) {
          sums[i] += delta;
          i += lowbit(i);
      }
    }

    public int query(int i) {       
      int sum = 0;
      while (i > 0) {
          sum += sums[i];
          i -= lowbit(i);
      }
      return sum;
    }    
};
private static int lowbit(int x) { return x & (-x); }