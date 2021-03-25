package lab3;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridWorker extends Application {
    private MyGrid grid;
    private ITask task1;
    private ITask task2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.grid=GridFabric.makeGrid();
        this.task1=new Task1(MyNodeBoxFabric.makeMyNodeBox("Task 1",0,0));
        this.task1=new Task1(MyNodeBoxFabric.makeMyNodeBox("Task 2",2,0));

        this.grid.getGrid().add(this.task1.getMyNodeBox().getvBox(), this.task1.getMyNodeBox().getStartColumn(),this.task1.getMyNodeBox().getStartRow());
        this.grid.getGrid().add(this.task2.getMyNodeBox().getvBox(), this.task2.getMyNodeBox().getStartColumn(),this.task2.getMyNodeBox().getStartRow());

        setHandlers();
    }

    private void setHandlers(){
        GoToHandler goToHandler=new GoToHandler(task1);
        InputHandler inputHandler=new InputHandler(task1);

        this.task1.getMyNodeBox().getInputButton().setOnAction(goToHandler);
        this.task1.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(inputHandler);

        GoToHandler goToHandler1=new GoToHandler(task2);
        InputHandler inputHandler1=new InputHandler(task2);

        this.task2.getMyNodeBox().getInputButton().setOnAction(goToHandler1);
        this.task2.getMyNodeBox().getGoToCriticalPlaceButton().setOnAction(inputHandler1);
    }

    private class GoToHandler implements EventHandler {
        private ITask task;
        public GoToHandler(ITask task){
            this.task=task;
        }
        @Override
        public void handle(Event event) {

        }
    }
    private class InputHandler implements EventHandler {
        private ITask task;
        public InputHandler(ITask task){
            this.task=task;
        }
        @Override
        public void handle(Event event) {

        }
    }

}
