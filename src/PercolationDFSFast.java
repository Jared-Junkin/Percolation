public class PercolationDFSFast extends PercolationDFS {
    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }
   /*
    @Override
    public boolean isFull(int row, int col) {
        if(inBounds(row, col)){
            return myGrid[row][col] == FULL;
        }
        return false;
    }

    @Override
    public boolean isOpen(int row, int col) {

        if(inBounds(row, col)){
            return myGrid[row][col] == OPEN;
        }
        return false;
    }
    */
    protected boolean AdjacentFull(int row, int col){
        if(!inBounds(row, col)) return false;
        if((inBounds(row+1, col) && isFull(row+1, col))
                || (inBounds(row-1, col) && isFull(row-1, col))
                || (inBounds(row, col+1) && isFull(row, col+1))
                || (inBounds(row, col-1) && isFull(row, col-1))){
            return true;
        }
        return false;
    }

    @Override
    protected void updateOnOpen(int row, int col){
        if (! inBounds(row,col)){
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if(AdjacentFull(row, col) || row == 0){
            dfs(row, col);
        }
    }
}
/*
if(isOpen(row - 1, col)){
            updateOnOpen(row - 1, col);
        }
        if(isOpen(row + 1, col)){
            updateOnOpen(row + 1, col);
        }
        if(isOpen(row, col + 1)){
            updateOnOpen(row, col + 1);
        }
        if(isOpen(row, col -1)){
            updateOnOpen(row, col -1);
        }
 */
/*
This is the inverted if statement, this works also
if(row == 0 || AdjacentFull(row, col)){
            myGrid[row][col] = FULL;
            if(isOpen(row - 1, col)){
                updateOnOpen(row - 1, col);
            }
            if(isOpen(row + 1, col)){
                updateOnOpen(row + 1, col);
            }
            if(isOpen(row, col + 1)){
                updateOnOpen(row, col + 1);
            }
            if(isOpen(row, col -1)){
                updateOnOpen(row, col -1);
            }
        }
 */