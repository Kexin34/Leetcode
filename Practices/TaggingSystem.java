import java.util.*;
import java.io.*;

/**
 * Created on:  Feb 03, 2021
 * Questions: https://aonecode.com/aplusplus/interviewctrl/getInterview/1189567720
 */

public class TaggingSystem {

    public static void main(String[] args) {
        System.out.println(largestScore("cbddd", 2));
    }

    // YOUR CODE HERE
    private static String largestScore(String str, int limit) {
        // 把字符串元素放到（类似哈希表中），每个字母对应出现次数
        int[] count = new int[26];
        for (char cur : str.toCharArray()) 
            count[cur - 'a']++;

        // 优先队列按照出现次数排列（若tie则比较char) 存的是[char, count]
        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> c1[0] == c2[0] 
            ? Integer.compare(c2[1], c2[1]) : Integer.compare(c2[0], c1[0]));
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) pq.add(new int[]{i, count[i]});
        }

        StringBuilder sb = new StringBuilder();
        if (pq.isEmpty()) return sb.toString();
        int[] pre = pq.poll();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            // Take the max of limit or count from the pre.
            if (pre[1] > limit) {       // 若超过，就先把limit个放进答案，再更新pq
                pre[1] -= limit;
                pq.add(pre);
                sb.append(getString(pre[0], limit));
            } else {                    // 不超过就直接放
                sb.append(getString(pre[0], pre[1]));
            }
            pre = cur;
        }
        // 把末尾剩余也append到答案里
        sb.append(getString(pre[0], Math.min(limit, pre[1])));
        return sb.toString();
    }

    private static String getString(int c, int count) {
        return ("" + (char) ('a' + c)).repeat(count);
    }
}