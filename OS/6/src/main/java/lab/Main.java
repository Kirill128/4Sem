package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lab.algs.impl.ListAlgImpl;
import lab.controller.Controller;
import lab.opmemory.MemoryBlock;
import lab.opmemory.OperationMemory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private static final int OPERATION_MEMORY_SIZE=64;
    private static final int SEGMENT_SIZE=4;

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller=this.setUpView(stage);

        this.setUpLogic(controller);
    }
    private Controller setUpView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view.fxml"));
        VBox rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        Controller controller = loader.getController();

        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();

        return controller;
    }
    private void setUpLogic(Controller controller){
        OperationMemory operationMemory=new OperationMemory(OPERATION_MEMORY_SIZE);
        int memoryBlocksCount=OPERATION_MEMORY_SIZE/SEGMENT_SIZE;
        List<MemoryBlock> memoryBlockList=new ArrayList<>(memoryBlocksCount);
        for(int i=0;i<memoryBlocksCount;i++){
            int startAddress=i*SEGMENT_SIZE;
            HBox box=new HBox();
            box.getChildren().add(new Label(String.valueOf(startAddress)));
            box.getChildren().add(new Label());//pid ...
            box.getChildren().add(new Label());//from to
            box.getChildren().add(new Label());//memory group
            memoryBlockList.add(new MemoryBlock(startAddress,SEGMENT_SIZE,box));
            controller.getOperationMemory().getChildren().add(box);
        }

        controller.setBiasAlg(new ListAlgImpl(SEGMENT_SIZE,operationMemory,memoryBlockList));
//        controller.setBiasAlg(new BiasAlgImpl(SEGMENT_SIZE,operationMemory,memoryBlockList));
    }

}
