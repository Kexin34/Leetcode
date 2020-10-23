//Keep Track of 3 Maximums Using a Set
class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> maxSet = new HashSet<>();
        for (int num : nums){
            maxSet.add(num);
            if (maxSet.size() > 3)
                maxSet.remove(Collections.min(maxSet));
        }
        // 有三个的话，返回set里最小的一个，不够三个返回最大的一个
        if (maxSet.size() == 3)
            return Collections.min(maxSet);
        return Collections.max(maxSet);
    }
}
// Time Complexity : O(n).
// Space Complexity : O(1).