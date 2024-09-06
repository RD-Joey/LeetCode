package com.stu.zyj;

import java.util.*;

public class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int[] ret = new int[k];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int m = mat.length, n = mat[0].length - 1;
        for (int i = 0; i < m; i++) {
            int mid, l = 0, r = n;
            while(l <= r) {
                mid = (l + r) >>> 1;
                if (mat[i][mid] == 0) r = mid - 1;
                else l = mid + 1;
            }
            pq.offer(new int[] { i, l });
        }
        for (int i = 0; i < k; i++)
            ret[i] = pq.poll()[0];
        return ret;
    }
    HashMap<String, TreeMap<String, Integer>> graph = new HashMap<>();
    List<String> itinerary = new ArrayList<>();
    int size;
    public List<String> findItinerary(List<List<String>> tickets) {
        size = tickets.size() + 1;
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            if (!graph.containsKey(from)) graph.put(from, new TreeMap<>());
            TreeMap<String, Integer> adj = graph.get(from);
            if (!adj.containsKey(to)) adj.put(to, 1);
            else adj.replace(to, adj.get(to) + 1);
        }
        helper("JFK");
        return itinerary;
    }
    void helper(String from) {
        itinerary.add(from);
        if (size == itinerary.size()) return;
        TreeMap<String, Integer> adj = graph.get(from);
        if (adj != null) {
            for (Map.Entry<String, Integer> to : adj.entrySet()) {
                if (to.getValue() > 0) {
                    to.setValue(to.getValue() - 1);
                    helper(to.getKey());
                    if (size == itinerary.size()) return;
                    to.setValue(to.getValue() + 1);
                }
            }
        }
        itinerary.remove(itinerary.size() - 1);
    }
}
