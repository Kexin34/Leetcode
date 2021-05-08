// 最大频率栈

class FreqStack {
    // 记录 FreqStack 中每个 val 对应的出现频率，后文就称为 VF 表
    HashMap<Integer, Integer> keyToFreq;
    // 记录频率 freq 对应的 val 列表，后文就称为 FV 表
    HashMap<Integer, Stack<Integer>> freqToKey;
    int maxFreq;  // 记录 FreqStack 中元素的最大频率
    
    public FreqStack() {
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        // 修改 VF 表：val 对应的 freq 加一
        int newFreq = keyToFreq.getOrDefault(val, 0) + 1;
        keyToFreq.put(val, newFreq);
        // 修改 FV 表：在 freq 对应的列表加上 val
        freqToKey.putIfAbsent(newFreq, new Stack<Integer>());
        freqToKey.get(newFreq).add(val);
        // 更新 maxFreq
        maxFreq = Math.max(maxFreq, newFreq);
    }
    
    public int pop() {
        // 修改 FV 表：pop 出一个 maxFreq 对应的元素 v
        Stack<Integer> vals = freqToKey.get(maxFreq);
        int v = vals.pop();
        // 修改 VF 表：v 对应的 freq (maxFreq) 减一
        keyToFreq.put(v, maxFreq - 1);
        // 更新 maxFreq
        if (vals.isEmpty()){
            maxFreq--;
        }
        return v;
    }
}
// Runtime: 25 ms, faster than 91.72% of Java
