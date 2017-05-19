

/**
 * Created on 5/18/2017.
 */
public class Cell {

    private CellState state;

    public CellState getState() {
        return state;
    }

    public void setAlive() {
        state = new AliveState();
    }

    public void setDead() {
        state = new DeadState();
    }
}
