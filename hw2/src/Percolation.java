import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[][] grid;
    private int openNum;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF noButtonUf;

    public Percolation(int N) {
        this.n = N;
        grid = new boolean[n][n];
        openNum = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);//虚拟Top + 虚拟Button
        noButtonUf = new WeightedQuickUnionUF(n * n + 1);//只有一个虚拟Top，避免回流现象
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {

            grid[row][col] = true;
            openNum += 1;

            int index = convert(row, col);
            //连接虚拟Top
            if (row == 0) {
                uf.union(index, n * n);
                noButtonUf.union(index, n * n);
            }

            //连接虚拟Button
            if (row == n - 1) {
                uf.union(index, n * n + 1);
            }

            //连接上下左右
            int[] u_d = new int[]{-1, 1, 0, 0};//x
            int[] l_r = new int[]{0, 0, -1, 1};//y

            for (int i = 0; i < 4; i++) {
                int next_x = row + u_d[i];
                int next_y = col + l_r[i];

                if (next_x >= 0 && next_y >= 0 && next_x <= n - 1 && next_y <= n - 1 && isOpen(next_x,next_y)) {
                    uf.union(convert(next_x, next_y), index);
                    noButtonUf.union(convert(next_x, next_y), index);
                }
            }

        }
    }

    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        return isOpen(row, col) && noButtonUf.connected(convert(row, col), n * n);
    }

    public int numberOfOpenSites() {
        return openNum;
    }

    public boolean percolates() {
        return uf.connected(n * n, n * n + 1);
    }

    int convert(int row, int col) {
        return row * n + col;
    }
}