import java.util.LinkedList;
import java.util.Queue;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Oliver Faris
 *
 */

public class MokshaPatam {

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    public static int fewestMoves(int boardsize, int[][] ladders, int[][] snakes) {
        // Array of nodes to see whether they were visited or not
        // Booleans are false initially
        boolean[] visited = new boolean[boardsize+1];

        // Make an array of the board but only changes values that are the beginnings of snakes or ladders
        int[] map = new int[boardsize+1];
        for (int i=0; i<ladders.length; i++) {
            int index = ladders[i][0];
            map[index] = ladders[i][1];
        }
        for (int i=0; i<snakes.length; i++) {
            int index = snakes[i][0];
            map[index] = snakes[i][1];
        }

        // Keeps track of how many rolls it takes to get to a certain value
        // Its boardsize + 1 to keep the values and square number consistent
        int[] rolls = new int[boardsize+1];

        // Make queue and set first node to 1
        Queue<Integer> nodes = new LinkedList<>();
        int currentNode = 1;
        nodes.add(currentNode);

        while (!nodes.isEmpty()) {
            currentNode = nodes.remove();

            // Check if game is won
            if (currentNode == boardsize) {
                return rolls[currentNode];
            }

            // Look at each roll
            for (int i=0; i<6; i++) {
                int node = currentNode +i +1;
                // Checks for a potential overflow error
                if (node > boardsize) {
                    break;
                }
                // If the node is a snake or ladder then the new node is the end of the snake or ladder
                if (map[node] != 0) {
                    node = map[node];
                }
                // If the node hasn't been visited before then add to the queue and update roll count
                if (visited[node] == false) {
                    visited[node] = true;
                    nodes.add(node);
                    rolls[node] = rolls[currentNode] + 1;
                }
            }
        }
        // Returns -1 if unsolvable
        return -1;
    }
}
