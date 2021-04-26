package lab.monitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lab.Main;

public class Runner extends Application {
    protected static Thread[] threads = new Thread[Main.PHILOSOPHERS_COUNT];

    public static void main(String[] args)  {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Runner.class.getResource("/form.fxml"));
        VBox rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        Controller controller = loader.getController();

        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.show();

        begin(controller);
    }
    public void begin(Controller controller)throws InterruptedException{
        System.out.println(System.nanoTime() + ": Dinner is started");

        Fork[] forks = new Fork[Main.PHILOSOPHERS_COUNT];
        forks[0] = new Fork(0,controller.fork1Place);
        forks[1] = new Fork(1,controller.fork2Place);
        forks[2] = new Fork(2,controller.fork3Place);
        forks[3] = new Fork(3,controller.fork4Place);
        forks[4] = new Fork(4,controller.fork5Place);
        forks[5] = new Fork(5,controller.fork6Place);

        Philosopher[] philosophers = new Philosopher[Main.PHILOSOPHERS_COUNT];
        State state = new State(philosophers);

        philosophers[0] = new Philosopher(0, forks[0], forks[5], state,controller.philosopher1Place,controller.philosopher1EatCount,controller.philosopher1State);
        philosophers[1] = new Philosopher(1, forks[0], forks[1], state,controller.philosopher2Place,controller.philosopher2EatCount,controller.philosopher2State);
        philosophers[2] = new Philosopher(2, forks[2], forks[1], state,controller.philosopher3Place,controller.philosopher3EatCount,controller.philosopher3State);
        philosophers[3] = new Philosopher(3, forks[2], forks[3], state,controller.philosopher4Place,controller.philosopher4EatCount,controller.philosopher4State);
        philosophers[4] = new Philosopher(4, forks[4], forks[3], state,controller.philosopher5Place,controller.philosopher5EatCount,controller.philosopher5State);
        philosophers[5] = new Philosopher(5, forks[4], forks[5], state,controller.philosopher6Place,controller.philosopher6EatCount,controller.philosopher6State);

        for (int i = 0; i < philosophers.length; i++) {
            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }


    }

    private static void printStats(Philosopher[] philosophers) {
        int totalCount = 0;
        for (Philosopher philosopher : philosophers) {
            totalCount += philosopher.eatCount.intValue();
        }
        if (totalCount > 0) {
            System.out.println("Eating stats:");
            System.out.println("Total: " + totalCount);
            for (int i = 0; i < philosophers.length; i++) {
                System.out.println(
                        "Philosopher " + (i + 1) + ": " + (100.0 * philosophers[i].eatCount.intValue() / totalCount) + "%");
            }
        }
    }

}
