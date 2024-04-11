import java.util.*;

public class ServerSelection {

    public static int getMinServers(int expected_load, List<Integer> serverIn) {
        // Convert serverIn list to array of capacities
        int n = serverIn.size();
        int[] servers = new int[n];
        for (int i = 0; i < n; i++) {
            servers[i] = serverIn.get(i);
        }

        // Set to store all powers of 2 up to 100
        Set<Integer> powerOfTwoSet = new HashSet<>();
        int power = 1;
        while (power <= 100) {
            powerOfTwoSet.add(power);
            power *= 2;
        }

        int minServers = Integer.MAX_VALUE;

        // Iterate over all subsets using bitmasking
        for (int mask = 1; mask < (1 << n); mask++) {
            int totalRequests = 0;
            int countServers = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    totalRequests += servers[i];
                    countServers++;
                }
            }

            if (totalRequests == expected_load) {
                minServers = Math.min(minServers, countServers);
            }
        }

        return (minServers == Integer.MAX_VALUE) ? -1 : minServers;
    }

    public static void main(String[] args) {
        // Example usage
        int expectedLoad = 3;
        List<Integer> servers = Arrays.asList(1, 1, 2, 4);

        int result = getMinServers(expectedLoad, servers);
        System.out.println(result);  // Output: 2
    }
}