// 
class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int pre = releaseTimes[0], max = pre;
        char maxChar = keysPressed.charAt(0);

        // 遍历一遍所有realeaseTimes数组，对每个key的时长都计算一遍，找出最大
        for (int i = 1; i < releaseTimes.length; i++) {
            int cur = releaseTimes[i];
            if (cur - pre > max || (cur - pre == max && keysPressed.charAt(i) > maxChar)) {
                maxChar = keysPressed.charAt(i);
                max = cur - pre;
            }
            pre = cur;
        }
        return maxChar;
    }
}
// faster than 100.00% of Java