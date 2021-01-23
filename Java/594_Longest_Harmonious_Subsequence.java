// 暴力枚举
class Solution {
    public int findLHS(int[] nums) {
        int maxCnt = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++){
            cnt = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] == nums[i])
                    cnt++;
                if (nums[j] == nums[i] + 1){
                    cnt++;
                    flag = true;
                }
            }
            if (flag)     //只有当flag为T，才保证数组存在有元素x+1，这才更新res值
                maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt;
    }
}
// TLE


// 最优解法：哈希表
class Solution {
    public int findLHS(int[] nums) {
        int maxCnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 遍历一边数组，得到hash map<element, frequ>
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        // 遍历哈希表，社当前遍历得到的键值对为（x, x's frequ）
        // 查询 x + 1在哈希映射中的值，就得到x和x+1出现的次数
        // 重点：只有当存在x+1的时候才计算
        for (int key : map.keySet()){
            if (map.containsKey(key + 1)){
                int cnt = map.get(key) + map.get(key + 1);
                maxCnt = Math.max(maxCnt, cnt);
            }   
        }
        return maxCnt;
    }
}
// faster than 89.32% of Java