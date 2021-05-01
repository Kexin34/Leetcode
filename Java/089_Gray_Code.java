//The first half of n=i is the same sequence as n=i-1
//The second half of n=i is add 2^(i-1) to the reversed sequence of first half.(保证相差1)

/*
n = 0: 0
n = 1: 0 | 1
n = 2: 00, 01 | 11, 10
n = 3: 000, 001, 011, 010 | 110, 111, 101, 100
f(n) = f(n - 1) | 2 ^ (n - 1) + f(n - 1)
*/

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        
        for (int i = 1; i <= n; i++) {
            int base = 1 << (i - 1);
            int size = res.size();
            for (int j = size - 1; j >= 0; j--)
                //新增:把之前的答案全部 OR (1 << (i - 1))
                res.add(res.get(j) + base);
        }
        return res;
    }
}
// Runtime: 5 ms, faster than 67.54% of Java
// Time complexity: O(2^n)
// Space complexity: O(2^n)