
import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) {
        super(n);
    }
    public Integer getVal(Integer r, Integer c){
        return r * myGrid.length + c;
    }

    public int[] getCoords(Integer myVal){
        return new int[]{myVal / myGrid.length, myVal % myGrid.length};
    }

    @Override
    public void dfs(int row, int col){
        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(getVal(row, col));
        myGrid[row][col] = FULL;
        while(myQueue.size() != 0) {
            //you keep the oldest ones in the queue and add the newest ones to the front, again and again until there are no more values
            int[] curr = getCoords(myQueue.poll());
            row = curr[0];
            col = curr[1];

            if (inBounds(row + 1, col) && myGrid[row + 1][col] == OPEN) {
                myGrid[row + 1][col] = FULL;
                myQueue.add(getVal(row+1, col));
            }
            if (inBounds(row - 1, col) && myGrid[row - 1][col] == OPEN) {
                myGrid[row - 1][col] = FULL;
                myQueue.add(getVal(row-1, col));
            }
            if (inBounds(row, col+1) && myGrid[row][col + 1] == OPEN) {
                myGrid[row][col + 1] = FULL;
                myQueue.add(getVal(row, col+1));
            }
            if (inBounds(row, col-1) && myGrid[row][col - 1] == OPEN) {
                myGrid[row][col - 1] = FULL;
                myQueue.add(getVal(row, col-1));
            }
        }
    }//If you do it the way I originally did, then I will get lots of repeats

}

/*
public PercolationDFS(int n) {
		myGrid = new int[n][n];
		myOpenCount = 0;
		for (int[] row : myGrid)
			Arrays.fill(row, BLOCKED);
	}
 */

/*
Dequeue a cell. (All cells in the queue should have been marked as FULL)
Check the dequeued cell’s four neighbors.
If the neighboring cell is open and not full, it should be marked as full and enqueued.
This is similar to the check in the recursive dfs method where cells that are marked as open but not full are visited by the recursive call.


 */