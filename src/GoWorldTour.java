import java.util.*;

public class GoWorldTour {

    public static int findMinimumCost(int treeNodes, List<Integer> treeFrom, List<Integer> treeTo, List<Integer> treeWeight, int start, int end) {
        // Create adjacency list for the tree
        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();
        for (int i = 0; i < treeNodes - 1; i++) {
            int u = treeFrom.get(i);
            int v = treeTo.get(i);
            int w = treeWeight.get(i);
            if (!adjacencyList.containsKey(u)) {
                adjacencyList.put(u, new ArrayList<>());
            }
            if (!adjacencyList.containsKey(v)) {
                adjacencyList.put(v, new ArrayList<>());
            }
            adjacencyList.get(u).add(new int[]{v, w});
            adjacencyList.get(v).add(new int[]{u, w});
        }

        // Initialize dp array
        int[][] dp = new int[1 << treeNodes][treeNodes + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[1 << (start - 1)][start] = 0;

        // Process all possible states using bitmasking
        for (int mask = 1; mask < (1 << treeNodes); mask++) {
            for (int u = 1; u <= treeNodes; u++) {
                if ((mask & (1 << (u - 1))) != 0) {
                    for (int[] neighbor : adjacencyList.getOrDefault(u, new ArrayList<>())) {
                        int v = neighbor[0];
                        int weight = neighbor[1];
                        if ((mask & (1 << (v - 1))) == 0) {
                            int nextMask = mask | (1 << (v - 1));
                            int currentCost = dp[mask][u];
                            int visits = Integer.bitCount(nextMask);
                            int edgeCost = (weight * ((visits + 1) / 2));
                            dp[nextMask][v] = Math.min(dp[nextMask][v], currentCost + edgeCost);
                        }
                    }
                }
            }
        }

        // Find the minimum cost to reach end node visiting all nodes at least once
        int minCost = Integer.MAX_VALUE;
        int finalMask = (1 << treeNodes) - 1;
        for (int u = 1; u <= treeNodes; u++) {
            if (u != end) {
                minCost = Math.min(minCost, dp[finalMask][u]);
            }
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        // Example usage
        int treeNodes = 4;
        List<Integer> treeFrom = Arrays.asList(1, 2, 2, 4);
        List<Integer> treeTo = Arrays.asList(2, 3, 4, 2);
        List<Integer> treeWeight = Arrays.asList(2, 3, 1, 4);
        int start = 1;
        int end = 4;

        int result = findMinimumCost(treeNodes, treeFrom, treeTo, treeWeight, start, end);
        System.out.println(result);  // Output: 9
    }
}
