package lab;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.Semaphore;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Semaphore sem = new Semaphore(3);
        Semaphore mutex=new Semaphore(1);
        new Philosopher(sem,1,mutex).start();
        new Philosopher(sem,2,mutex).start();
        new Philosopher(sem,3,mutex).start();
        new Philosopher(sem,4,mutex).start();
        new Philosopher(sem,5,mutex).start();
        new Philosopher(sem,6,mutex).start();
    }
}
