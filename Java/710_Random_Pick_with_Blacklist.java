class Solution {
    int size;
    Map<Integer, Integer> map; // 存放着黑名单数字，和它对应的合法数字index
    Random rand = new Random();
    
    public Solution(int N, int[] blacklist) {
        map = new HashMap<>();
        size = N - blacklist.length;    // 最终数组中的元素个数
        int last = N - 1;               // 最后一个元素的索引
        
        // 先将所有黑名单数字加入 map
        for (int b : blacklist)
            // 这里赋值多少都可以
            // 目的仅仅是把键存进哈希表
            // 方便快速判断数字是否在黑名单内
            map.put(b, 666);
        
        for (int b : blacklist) {
            // 如果 b 已经在区间 [sz, N), 可以直接忽略
            if (b >= size) continue;
            // 跳过所有黑名单中的数字
            while (map.containsKey(last))
                last--;
            // 将黑名单中的索引映射到合法数字
            map.put(b, last);
            last--;
        }
    }
    
    public int pick() {
        // 随机选取一个索引
        int index = rand.nextInt(size);
        // 这个索引命中了黑名单，需要被映射到它对应的合法数字位置
        if (map.containsKey(index))
            return map.get(index);
        // 若没命中黑名单，则直接返回
        return index;
    }
}
// Runtime: 38 ms, faster than 73.57% of Java 
        