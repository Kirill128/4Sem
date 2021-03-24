package lab3;

import javafx.scene.layout.GridPane;

public class MyGrid {
    public static final int CRITICAL_PLACE_ROW=1;
    public static final int CRITICAL_PLACE_COLUMN=2;
    private GridPane grid;
    private boolean someInCriticalPlace;

    public MyGrid(GridPane grid, boolean someInCriticalPlace) {
        this.grid = grid;
        this.someInCriticalPlace = someInCriticalPlace;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public boolean isSomeInCriticalPlace() {
        return someInCriticalPlace;
    }

    public void setSomeInCriticalPlace(boolean someInCriticalPlace) {
        this.someInCriticalPlace = someInCriticalPlace;
    }
}
