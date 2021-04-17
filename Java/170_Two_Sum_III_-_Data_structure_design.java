class TwoSum {
    Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int key: map.keySet()){
            if (!map.containsKey(value - key)) 
                continue;
            else{
                if (value != key * 2) return true;
                else if (map.get(key) > 1) return true;
            }
        }
        return false;
    }
}
// Runtime: 89 ms, faster than 58.20% of Java 
// 复杂度：find o(n) add o(1)