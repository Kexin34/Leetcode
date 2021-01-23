// 解法：利用set
// 优化：只有当curNum遇到一个序列的开始，while才被执行

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxCnt = 0;
        for (int num : nums)
            set.add(num);
        
        for (int num : set){
            // 只对于（当前数字-1）不在哈希表的数字，
            // 作为连接序列的第一个数字去找对应的最长序列
            if (!set.contains(num - 1)){
                int curNum = num;
                int curStreak = 1;
                while (set.contains(curNum + 1)){
                    curStreak++;
                    curNum++;
                }
                maxCnt = Math.max(maxCnt, curStreak);
            }
        }
        return maxCnt;
    }
}
// faster than 86.51% of Java
// Time：O(N)， Space： O(N)