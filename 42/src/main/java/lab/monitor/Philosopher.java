package lab.monitor;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lab.Main;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher implements Runnable {
    private Rectangle rectanglePlace;
    private Label eatCountText;
    private Label philosopherStateLabel;

    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    private final State state;
    public final AtomicBoolean stopped = new AtomicBoolean();

    public AtomicInteger eatCount = new AtomicInteger(0);

    protected Philosopher(int id, Fork leftFork, Fork rightFork, State state,Rectangle rectanglePlace,Label eatCountText, Label philosopherStateLabel) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = state;
        this.eatCountText=eatCountText;
        this.rectanglePlace=rectanglePlace;
        this.philosopherStateLabel=philosopherStateLabel;
    }

    @Override
    public void run() {
        try {
            while (!stopped.get()) {
                think();
                hungry();
                state.takeForks(id, leftFork, rightFork);
                eat();
                state.putForks(id, leftFork, rightFork);
            }
        } catch (InterruptedException ignored) { }
    }
    private void think() throws InterruptedException {

        long t = System.nanoTime();
            System.out.println(t + ": " + Thread.currentThread().getName() + " is thinking");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    philosopherStateLabel.setText("Thinking");
                    rectanglePlace.setVisible(false);
                    philosopherStateLabel.setTextFill(Color.BLUE);
                }
            });

        if (Main.MAX_WAIT_MS > 0) {
            Thread.sleep(getRandomInt());
        }
    }
    private void hungry(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                philosopherStateLabel.setText("Hungry");
                philosopherStateLabel.setTextFill(Color.RED);
            }
        });
    }

    private void eat() throws InterruptedException {
        eatCount.incrementAndGet();
        long t = System.nanoTime();
            System.out.println(t + ": " + Thread.currentThread().getName() + " is eating");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    philosopherStateLabel.setText("Eating");
                    philosopherStateLabel.setTextFill(Color.GREEN);
                    rectanglePlace.setVisible(true);
                    eatCountText.setText(String.valueOf(eatCount.get()));
                }
            });

        if (Main.MAX_WAIT_MS > 0) {
            Thread.sleep(getRandomInt());
        }

    }

    private int getRandomInt() {
        return (int) (new Random().nextInt(Main.MAX_WAIT_MS)+Main.MIN_WAIT_MS);
    }
}
