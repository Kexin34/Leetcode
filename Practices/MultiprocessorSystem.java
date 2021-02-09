// 答案错的，
import java.util.*;
class Solution {
    public static int multiprocessorSystem(int[] ability, int processes){
        // WRITE YOUR BRILLIANT CODE HERE
PriorityQueue<Integer> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p2, p1));
        for (int cap : ability) {
            pq.add(cap);
        }
        int time = 0, processed = 0;
        while (!pq.isEmpty() && processed < processes) {
            int poll = pq.poll();
            processed += poll;
            time++;
            if (poll / 2 > 0) pq.add(poll / 2);
        }
        return time;

    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int[] ability  = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int processes = Integer.parseInt(scanner.nextLine());
        scanner.close();
        System.out.println(multiprocessorSystem(ability, processes));
    }
}
