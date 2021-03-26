package lab3;

import javafx.scene.layout.GridPane;

public class MyGrid {
    public static final int CRITICAL_PLACE_ROW=1;
    public static final int CRITICAL_PLACE_COLUMN=2;
    private  GridPane grid;
    private  Boolean someInCriticalPlace;

    public MyGrid(GridPane grid, Boolean someInCriticalPlace) {
        this.grid = grid;
        this.someInCriticalPlace = someInCriticalPlace;
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public Boolean isSomeInCriticalPlace() {
        return someInCriticalPlace;
    }

    public void setSomeInCriticalPlace(Boolean someInCriticalPlace) {
        this.someInCriticalPlace = someInCriticalPlace;
    }
}
