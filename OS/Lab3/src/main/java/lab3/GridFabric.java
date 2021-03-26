package lab3;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridFabric {
    private static int windowWidth=800;
    private static int windowHeight=600;
    public static MyGrid makeGrid(){
        GridPane grid=new GridPane();
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/3));
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/3));
        grid.getColumnConstraints().add(new ColumnConstraints(windowWidth/3));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        grid.getRowConstraints().add(new RowConstraints(windowHeight/3));
        return new MyGrid(grid,false);
    }
}
