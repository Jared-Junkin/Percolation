import java.security.KeyStore;

public class PercolationUF implements IPercolate {
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;
    private IUnionFind myFinder;

    public Integer getIndex(Integer r, Integer c){
        return r * myGrid.length + c;
    }

    public int[] getCoords(Integer myVal){
        return new int[]{myVal / myGrid.length, myVal % myGrid.length};
    }

    public PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean[size][size];
        VTOP = size*size;
        VBOTTOM = size*size + 1;
        finder.initialize(size*size + 2);
        myFinder = finder;
        /*
        The constructor should initialize the IUnionFind object of size NxN + 2 by calling finder.initialize
        appropriately and then storing this object in the appropriate instance variable which is myFinder.

As with the Queue described in PercolationBFS above you'll need to map a (row,col) pair to an int value
to use with the IUnionFind object referenced by myFinder.  Be sure to deal with VTOP and VBOTTOM as well.

I don't really get what IUnionFind and IPercolate actually do
Or what VTOP and VBOTTOM are meant to represent
Or how I'm supposed to use them to find whether the cell should be marked as full.
I am thinking about using some sort of ratrun thing to make it go.
I also don't think my BFS is working

         */
    }
//I don't understand what VTop and VBottom are supposed to do.
    @Override
    public void open(int row, int col) {
        if(!inBounds(row, col)){
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        if(myGrid[row][col] == false){
            myGrid[row][col] = true;
        }
        if(inBounds(row-1, col) && isOpen(row-1, col)){
            myFinder.union(getIndex(row,col), getIndex(row-1, col));
        }
        if(inBounds(row+1, col) && isOpen(row+1, col)){
            myFinder.union(getIndex(row,col), getIndex(row+1, col));
        }
        if(inBounds(row, col-1) && isOpen(row, col-1)){
            myFinder.union(getIndex(row,col), getIndex(row, col-1));
        }
        if(inBounds(row, col+1) && isOpen(row, col+1)){
            myFinder.union(getIndex(row,col), getIndex(row, col+1));
        }
        if(row == 0){
            myFinder.union(getIndex(row,col), VTOP);
        }
        if(row == myGrid.length-1){
            myFinder.union(getIndex(row,col), VBOTTOM);
        }
        myOpenCount++;
    }

    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(getIndex(row,col), VTOP);

    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
