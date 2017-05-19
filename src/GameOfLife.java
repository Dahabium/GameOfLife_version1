

/**
 * Created on 5/18/2017.
 */
@SuppressWarnings("Duplicates")
public class GameOfLife {

    private GameOfLifeUI observer;
    private Cell[][] grid;

    public GameOfLife(int numbrows, int numbcols, GameOfLifeUI observer) {
        this.observer = observer;

        // init grid
        grid = new Cell[numbcols][numbrows];
        for (int i=0; i<numbcols; i++) {
            for (int j = 0; j < numbrows; j++) {
                grid[i][j] = new Cell();
                grid[i][j].setDead();
            }
        }
        // choose some initial configuration
        // multiple inits might interfere with the shape behavior
        initGlider();
        initSmallExploder();
        initTumbler();

        observer.notifyMe(grid);
    }


    public void advance() {
        Cell[][] newgrid = new Cell[grid.length][grid[0].length];

        for (int i=0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                newgrid[i][j] = new Cell();
                newgrid[i][j].setDead();
            }
        }

        for (int i=0; i<grid.length; i++)
            for (int j=0; j<grid[0].length; j++)
                if ((grid[i][j]).getState().isAlive() && (nbrOfNeighbors(i,j) < 2))
                    newgrid[i][j].setDead();
                else if ((grid[i][j]).getState().isAlive() && (2 <= nbrOfNeighbors(i,j)) && (nbrOfNeighbors(i,j) <= 3))
                    newgrid[i][j].setAlive();
                else if ((grid[i][j]).getState().isAlive() && (3 < nbrOfNeighbors(i,j)))
                    newgrid[i][j].setDead();
                else if (!(grid[i][j]).getState().isAlive() && (nbrOfNeighbors(i,j) == 3))
                    newgrid[i][j].setAlive();

        grid = newgrid;
        observer.notifyMe(grid);
    }

    private int nbrOfNeighbors(int x, int y) {
        int result = 0;
        if ((0 <= x-1) && (0 <= y-1) && (grid[x-1][y-1]).getState().isAlive()) result++;
        if ((0 <= x-1) && (grid[x-1][y]).getState().isAlive()) result++;
        if ((0 <= x-1) && (y+1 < grid[0].length) && (grid[x-1][y+1]).getState().isAlive()) result++;
        if ((0 <= y-1) && (grid[x][y-1]).getState().isAlive()) result++;
        if ((y+1 < grid[0].length) && (grid[x][y+1]).getState().isAlive()) result++;
        if ((x+1 < grid.length) && (0 <= y-1) && (grid[x+1][y-1]).getState().isAlive()) result++;
        if ((x+1 < grid.length) && (grid[x+1][y]).getState().isAlive()) result++;
        if ((x+1 < grid.length) && (y+1 < grid[0].length) && (grid[x+1][y+1]).getState().isAlive()) result++;
        return result;
    }


    private void initSmallExploder() {
        grid[30][31].setAlive();
        grid[30][32].setAlive();
        grid[31][30].setAlive();
        grid[31][31].setAlive();
        grid[31][33].setAlive();
        grid[32][31].setAlive();
        grid[32][32].setAlive();
    }

    private void initGlider() {
        grid[21][20].setAlive();
        grid[22][21].setAlive();
        grid[22][22].setAlive();
        grid[21][22].setAlive();
        grid[20][22].setAlive();
    }

    private void initTumbler() {
        grid[30][23].setAlive();
        grid[30][24].setAlive();
        grid[30][25].setAlive();
        grid[31][20].setAlive();
        grid[31][21].setAlive();
        grid[31][25].setAlive();
        grid[32][20].setAlive();
        grid[32][21].setAlive();
        grid[32][22].setAlive();
        grid[32][23].setAlive();
        grid[32][24].setAlive();
        grid[34][20].setAlive();
        grid[34][21].setAlive();
        grid[34][22].setAlive();
        grid[34][23].setAlive();
        grid[34][24].setAlive();
        grid[35][20].setAlive();
        grid[35][21].setAlive();
        grid[35][25].setAlive();
        grid[36][23].setAlive();
        grid[36][24].setAlive();
        grid[36][25].setAlive();
    }
}
