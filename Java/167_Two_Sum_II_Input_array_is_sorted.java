class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) return null;
        int lo = 0;
        int hi = numbers.length - 1;
        int sum;
        
        while(lo < hi){
            sum = numbers[lo] + numbers[hi];
            if(sum == target)
                return new int[] {lo + 1, hi + 1};
            else if (sum < target)
                lo++;
            else if (sum> target)
                hi--;
        }
        return null;
    }
}