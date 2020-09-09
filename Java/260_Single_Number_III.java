// 解法一：HashSet （我自己想的）
// 出现两次的话set会remove掉，所以最后set只剩下出现过单数次的元素
class Solution {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            if (!set.contains(num)) 
                set.add(num);
            else 
                set.remove(num);
        }
        int i = 0;
        for (int num : set)
            res[i++] = num;
        return res;
    }
}
// Time complexity : O(N) to iterate over the input array.
// Space complexity : O(N) to keep the hash of N elements.


// 解法二： HashMap 
// 记录 <数字，出现频率>,loop完检查freq = 1的元素就为答案
class Solution {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums)      // 统计出现频率
            map.put(n, map.getOrDefault(n, 0) + 1);

        int idx = 0;
        // 遍历哈希表，找出entry value为1的key，放到res中
        for (Map.Entry<Integer, Integer> item : map.entrySet()){
            if (item.getValue() == 1)
                res[idx++] = item.getKey();
        }
        return res;
    }
}
// Time complexity : O(N) to iterate over the input array.
// Space complexity : O(N) to keep the hash of N elements.




// 解法三：两个掩码
// 关键点怎么把a^b分成两部分,方案：可以通过diff最后一个1区分
class Solution {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int n : nums)
            bitmask ^= n;
        
        // 用这个bitmask as a marker to separate x and y.
        int diff = bitmask & (-bitmask); // isolate the rightmost 1-bit
        int[] res = new int[2];
        
        for (int num : nums){
            if ((num & diff) == 0)  //如果最右侧位1的和当前数字不同
                res[0] ^= num;
            else
                res[1] ^= num;
        }
        return res;
    }
}
//  faster than 99.90% of Java
// Time complexity : O(N) to iterate over the input array.
// Space complexity :O(1), it's a constant space solution.