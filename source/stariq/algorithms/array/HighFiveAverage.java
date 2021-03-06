package stariq.algorithms.array;

import java.util.*;

// Question in data
// https://leetcode.com/problems/high-five/
// Find the average score of each student's top five scores in the order of their student id.
public class HighFiveAverage {

    public static void main(String[] args) {
        int[][] arr = new int[][] {{1,91}, {1,92}, {2,93}, {2,97}, {1,60}, {2,77},
                {1,65}, {1,87}, {1,100}, {2,100}, {2,76}};

        int[][] result = topFive2(arr);
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] topFive(int[][] arr) {
        TreeMap<Integer, PriorityQueue<Integer>> recordsMap = new TreeMap<>();

        for(int[] item : arr) {
            int id = item[0];
            int score = item[1];
            PriorityQueue<Integer> scoresQueue;
            if(recordsMap.containsKey(id)) {
                scoresQueue = recordsMap.get(id);
                scoresQueue.offer(score);
                if(scoresQueue.size() > 5) {
                    scoresQueue.poll();
                }
                recordsMap.put(id, scoresQueue);
            } else {
                scoresQueue = new PriorityQueue<>();
                scoresQueue.offer(score);
                recordsMap.put(id, scoresQueue);
            }
        }

        int[][] result = new int[recordsMap.size()][2];
        for(int id : recordsMap.keySet()) {
            PriorityQueue<Integer> scoresQueue = recordsMap.get(id);
            int sum = 0;
            while(!scoresQueue.isEmpty()) {
                sum += scoresQueue.poll();
            }
            result[id - 1][0] = id;
            result[id - 1][1] = sum / 5;

        }
        return result;
    }

    public static int[][] topFive2(int[][] arr) {
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();

        for(int[] i : arr) {
            PriorityQueue<Integer> pq = map.get(i[0]);
            if(pq == null) {
                pq = new PriorityQueue<>();
                pq.add(i[1]);
                map.put(i[0], pq);
            } else {
                pq.add(i[1]);
                if(pq.size() > 5) {
                    pq.poll();
                }
            }
        }

        int[][] result = new int[map.size()][2];
        for(Map.Entry<Integer, PriorityQueue<Integer>> e : map.entrySet()) {
            int average = 0;
            while(!e.getValue().isEmpty()) {
                average += e.getValue().poll();
            }
            average = average/5;
            result[e.getKey() - 1][0] = e.getKey();
            result[e.getKey() - 1][1] = average;

        }
        return result;
    }
}
