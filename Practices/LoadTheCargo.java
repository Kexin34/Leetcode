import java.util.*;
import java.io.*;

/**
 * Created on:  Feb 03, 2021
 * Questions: https://aonecode.com/aplusplus/interviewctrl/getInterview/118748234179
 */

public class LoadTheCargo {

    public static void main(String[] args) {
        // System.out.println(loadTheCargo(3, new int[]{1, 2, 3}, 3, new int[]{3, 2, 1}, 3) + " = 7");
        System.out.println(loadTheCargo(3, new int[]{3, 1, 2}, 3, new int[]{1,2,3}, 4) + " = 9");
    }

    public static int loadTheCargo(int num, int[] containers, int itemSize, int[] itemsPerContainer, long cargoSize) {
        // 按照可装载能力，从大到小排
        PriorityQueue<int[]> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c2[1], c1[1]));

        // 0: counts, 1: itemSize
        for (int i = 0; i < num; i++) {
            pq.add(new int[]{containers[i], itemsPerContainer[i]});
        }

        long totalItems = 0, totalCargos = 0;

        while (totalCargos < cargoSize && !pq.isEmpty()) {
            int[] poll = pq.poll();

            // 如果在size限制范围内，Then take all the items
            if (totalCargos + poll[0] <= cargoSize) {
                totalItems += poll[0] * poll[1];
                totalCargos += poll[0];
            // 如果超出size限制范围，求出剩余可装载个数再装
            } else {
                long canLoad = cargoSize - totalCargos;
                totalItems += canLoad * poll[1];
                totalCargos += canLoad;
            }
        }
        return (int) totalItems;
    }
}