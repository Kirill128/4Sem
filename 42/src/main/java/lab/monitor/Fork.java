package lab.monitor;

import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

public class Fork {
    private boolean availability = true;
    private CheckBox forkBox;
    private int id;

    public Fork(int id,CheckBox forkBox) {
        this.id=id;
        this.forkBox=forkBox;
    }

    public void take() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long t = System.nanoTime();
            System.out.println(t + ": Fork " + id + " is taken");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    forkBox.setSelected(true);
                    forkBox.setTextFill(Color.BLACK);
                }
            });
//        }
    }

    public void put() {
        long t = System.nanoTime();
            System.out.println(t + ": Fork " + id + " is put");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    forkBox.setSelected(false);
                    forkBox.setTextFill(Color.WHITE);
                }
            });
    }

    public int getId() {
        return id;
    }


    public boolean getAvailability(){
        return availability;
    }

    public void setAvailability(boolean flag){
        availability = flag;
    }



}
