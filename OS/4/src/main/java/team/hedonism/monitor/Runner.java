package team.hedonism.monitor;

import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import team.hedonism.Main;

public class Runner extends Application {
    protected static Thread[] threads = new Thread[Main.PHILOSOPHERS_COUNT];

    public static void main(String[] args)  {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("form.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        stage.setWidth(1800);
        stage.setHeight(1000);
        stage.show();
    }
    public void begin()throws InterruptedException{
        System.out.println(System.nanoTime() + ": Dinner is started");

        Fork[] forks = new Fork[Main.PHILOSOPHERS_COUNT];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        Philosopher[] philosophers = new Philosopher[Main.PHILOSOPHERS_COUNT];
        State state = new State(philosophers);

        philosophers[0] = new Philosopher(0, forks[0], forks[5], state);
        philosophers[1] = new Philosopher(1, forks[0], forks[1], state);
        philosophers[2] = new Philosopher(2, forks[2], forks[1], state);
        philosophers[3] = new Philosopher(3, forks[2], forks[3], state);
        philosophers[4] = new Philosopher(4, forks[4], forks[3], state);
        philosophers[5] = new Philosopher(5, forks[4], forks[5], state);

        for (int i = 0; i < philosophers.length; i++) {
//            Fork leftFork = forks[i];
//            Fork rightFork = forks[(i + 1) % forks.length];
//            philosophers[i] = new Philosopher(i, leftFork, rightFork, state);

            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }

//        waitMillis(Main.DINNER_DURATION_IN_MS);
//        stopPhilosophers(philosophers);
//        stopThreads(threads);
//
//        System.out.println(System.nanoTime() + ": Dinner is finished");
        while(true){
            waitMillis(Main.DINNER_DURATION_IN_MS);
            printStats(philosophers);
        }
    }
    private static void stopPhilosophers(Philosopher[] philosophers) throws InterruptedException {
        for (Philosopher philosopher: philosophers) {
            if (!philosopher.stopped.get()) {
                philosopher.stop();
            }
        }
        waitMillis(200);
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



    protected static void waitMillis(long millis) {
        if (millis <= 0) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected static void stopThreads(Thread[] threads) {
        for (Thread thread : threads) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
            }
        }
        waitMillis(100);
    }


}
