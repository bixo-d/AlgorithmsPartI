
/******************************************************************************
 * Compilation: javac RandomWord.java
 * 
 * Execution: java RandomWord
 *
 * Prints "Hello, World". By tradition, this is everyone's first program.
 *
 * % java RandomWord Hello, World
 *
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // private int[] modelTree;
    private boolean[] model;
    private int dimension = 0;
    private int lenght = 0;
    private int openSites = 0;
    public edu.princeton.cs.algs4.QuickUnionUF modelTree;

    private int xyToUF(int row, int col) {
        // System.out.print(" " + row + (col * this.n) + " ");
        int value = col + (row * this.dimension) + 1;
        if (value < 0) {
            value = 0;
        } else if (value > this.lenght - 1) {
            value = this.lenght - 1;
        }
        return value;
    }

    private void union(int row, int col) {
        int site = this.xyToUF(row, col);
        // System.out.println("row " + row + " col  " + col + " uf " + this.xyToUF(row, col));
        // System.out.println("top " + this.xyToUF(row - 1, col) + " bottom " + this.xyToUF(row + 1, col));
        // System.out.println("left " + this.xyToUF(row, col - 1) + " right " + this.xyToUF(row, col + 1));

        // connect with top
        int top = this.xyToUF(row - 1, col);
        if (row < 1)
            top = 0;
        this.modelTree.union(site, top);
        // System.out.println("Connecting " + site + " with " + top + " at top");

        // connect with bottom
        int bottom = this.xyToUF(row + 1, col);
        if (row >= this.dimension)
            bottom = this.dimension + 1;
        this.modelTree.union(site, bottom);
        // System.out.println("Connecting " + site + " with " + bottom + " at bottom");

        // connect with left
        if (col >= 1) {
            int left = this.xyToUF(row, col - 1);
            this.modelTree.union(site, left);
            // System.out.println("Connecting " + site + " with " + left + " at left");
        }

        // connect with right
        if (col < this.dimension - 1) {
            int right = this.xyToUF(row, col + 1);
            this.modelTree.union(site, right);
            // System.out.println("Connecting " + site + " with " + right + " at right");
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int dimension) {
        this.dimension = dimension;
        this.lenght = dimension * dimension + 2;
        this.model = new boolean[this.lenght];

        this.modelTree = new edu.princeton.cs.algs4.QuickUnionUF(this.lenght);

        this.model[0] = true;
        this.model[this.lenght - 1] = true;
        // this.modelTree = new int[n * n + 2];
        // for (int i = 0; i < this.n + 2; i++) this.modelTree[i] = i;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            this.openSites++;
            this.model[xyToUF(row, col)] = true;
            this.union(row, col);
            // Here have I to connect with the adjacent open cells?
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return this.model[xyToUF(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return (this.modelTree.find(xyToUF(row, col)) == 0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        // In order to NOT count open sites, once one site is opened
        // the var openSites is incremented, so it can be returned here
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // There are two virtual sites 0 is top and n + 1 is bottom
        // That represents the top and bottom of the matrix
        // If 0 and n + 1 are connected, then there is percolation
        return (this.modelTree.find(0) == this.lenght - 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        int dim = 10;
        Percolation percolation = new Percolation(dim);

        // for (int i = 0; i <  dim * dim + 2; i++) {
        //     System.out.println("find(" + i + ") = " + percolation.modelTree.find(i));
        // }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.println("[" + i + "][" + j + "]" + percolation.isOpen(j, i));
            }
        }
        System.out.println(percolation.percolates());

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                percolation.open(i, j);
                System.out.println("[" + i + "][" + j + "]" + percolation.isOpen(j, i));
            }
        }
        // for (int i = 0; i <  dim * dim + 2; i++) {
        //     System.out.println("find(" + i + ") = " + percolation.modelTree.find(i));
        // }
        System.out.println(percolation.percolates());
    }
}