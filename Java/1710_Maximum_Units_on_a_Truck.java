// Greedy
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // 先把box类型按照单个容量从大到小排列
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        
        int res = 0;
        // Greedy 选择
        for (int[] box: boxTypes){
            res += box[1] * Math.min(box[0], truckSize);
            truckSize = truckSize - box[0];
            if (truckSize <= 0) 
                break;
        }
        return res;
    }
}
// Time complexity: O(nlogn)
// Space complexity: O(1)
