class Solution {
    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        x = Math.abs(x);
        int res = 0;
        
        while (x > 0) {
            // checking overflow: If overflow exists, the new result will not equal previous one.
            // 要保证下面的【res * 10要小于MAX】并且【res * 10 + x % 10 要小于MAX】
            if (Integer.MAX_VALUE / 10 < res || Integer.MAX_VALUE - x % 10 < res * 10)
                return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return sign * res;
    }
}
// faster than 100.00% of Java