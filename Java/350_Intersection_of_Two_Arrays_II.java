class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //base on number frequency of nums1
        for (int i : nums1){
            int freq = map.getOrDefault(i, 0);
            map.put(i, freq + 1);
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2){
            if (map.get(i) != null && map.get(i) > 0){
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        
        return res;
    }
}
// faster than 98.84% of Java 
// O(N + M) time complexity, O(N) for iterate one of the array 
// to create a hashmap and O(M) to iterate the other array. 
// O(N) space to store such hashmap.


// Follow up: 
// What if the given array is already sorted?
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;){
            if (nums1[i] < nums2[j]) 
                i++;
            else if (nums1[i] > nums2[j])
                j++;
            else{
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        int res[] = new int[list.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = list.get(i);
        return res;
    }
}