// 解法：利用HashMap和数组
class RandomizedSet {
    private Map<Integer, Integer> map;
    private ArrayList<Integer> array;
    Random rand = new Random();
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>(); // <value, index in array>
        array = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, array.size());
        array.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);       // 待删除的index
        
        // 如果val不是数组最后一个，交换数组val和数组末尾元素
        if (index < array.size() - 1 ){
            int lastVal = array.get(array.size() - 1 );// 末尾元素值
            array.set(index , lastVal);// 把待删除index的值设成末尾元素值
            map.put(lastVal, index);    // 哈希表末尾元素的对应array位置更新
        }
        map.remove(val);
        array.remove(array.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return array.get(rand.nextInt(array.size()) );
    }
}
// faster than 100.00% of Java
