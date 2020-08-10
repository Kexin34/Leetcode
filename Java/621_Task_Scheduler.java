// Greedy

class Solution {
    public int leastInterval(char[] tasks, int n) {
        // frequencies of the tasks
        int[] freq = new int[26];
        for (int t : tasks)
            freq[t - 'A']++;
        
        Arrays.sort(freq); //ascending
        
        // max frequency
        int f_max = freq[25];
        int idleTime = (f_max - 1) * n; // maximun possible idle slots,
        
        // To compute the minimum number of idle slots, i 从倒数第二大开始往前
        for (int i = freq.length - 2; i >= 0 && idleTime > 0; --i)
            idleTime -= Math.min(f_max - 1, freq[i]);
        
        idleTime = Math.max(0, idleTime);
        return idleTime + tasks.length;
    }
}
// faster than 99.83% of Java 