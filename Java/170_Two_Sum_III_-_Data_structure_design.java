class TwoSum {
    HashMap<Integer, Integer> freq;
    
    /** Initialize your data structure here. */
    public TwoSum() {
        freq = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        // 记录 number 出现的次数
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Integer num : freq.keySet()) {
            int other = value - num;
            // 情况一
            if (other == num && freq.get(other) > 1)
                return true;
            // 情况二
            if (other != num && freq.containsKey(other))
                return true;
            
        }
        return false;
    }
}
// Runtime: 83 ms, faster than 75.13% of Java
// 复杂度：find o(n) add o(1)