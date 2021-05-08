class LFUCache {
    // key 到 val 的映射，我们后文称为 KV 表
    HashMap<Integer, Integer> keyToVal;
    // key 到 freq 的映射，我们后文称为 KF 表
    HashMap<Integer, Integer> keyToFreq;
    // freq 到 key 列表的映射，我们后文称为 FK 表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKey;//保持插入的时序 + O(1) 时间内访问或删除
    // 记录最小的频次
    int minFreq;
    // 记录 LFU 缓存的最大容量
    int cap;
    
    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        minFreq = 0;
        cap = capacity;
    }
    
    public int get(int key) {
        if (!keyToVal.containsKey(key))
            return -1;
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return;
        
        /* 若 key 已存在，修改对应的 val 即可 */
        if (keyToVal.containsKey(key)){
            keyToVal.put(key, value);
            increaseFreq(key);// key 对应的 freq 加一
            return;
        }
        
        /* key 不存在，需要插入 */
        /* 容量已满的话需要淘汰一个 freq 最小的 key */
        if (keyToVal.size() >= cap) {
            removeMinFreqKey();
        }
        /* 插入 key 和 val，对应的 freq 为 1 */
        // 插入 KV 表
        keyToVal.put(key, value);
        // 插入 KF 表
        keyToFreq.put(key, 1);
        // 插入 FK 表
        freqToKey.putIfAbsent(1, new LinkedHashSet<>());
        freqToKey.get(1).add(key);
        // 插入新 key 后最小的 freq 肯定是 1
        minFreq = 1;
        
    }
    
    /* 辅助函数 */
    private void removeMinFreqKey() {
        // freq 最小的 key 列表
        LinkedHashSet<Integer> keyList = freqToKey.get(minFreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        int deleteKey = keyList.iterator().next();
        
        /* 更新 FK 表 */
        keyList.remove(deleteKey);
        if (keyList.isEmpty())
            freqToKey.remove(minFreq);
        /* 更新 KV 表 */
        keyToVal.remove(deleteKey);
        /* 更新 KF 表 */
        keyToFreq.remove(deleteKey);
    }
    
    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        /* 更新 KF 表 */
        keyToFreq.put(key, freq + 1);
        
        /* 更新 FK 表 */
        // 将 key 从 freq 对应的列表中删除
        freqToKey.get(freq).remove(key);
        // 将 key 加入 freq + 1 对应的列表中
        freqToKey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKey.get(freq + 1).add(key);
        
        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKey.get(freq).isEmpty()){
            freqToKey.remove(freq);
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq == minFreq)
                minFreq++;
        }    
    }
}
// Runtime: 44 ms, faster than 18.40% of Java

