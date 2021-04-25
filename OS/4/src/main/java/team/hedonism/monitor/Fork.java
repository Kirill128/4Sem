package team.hedonism.monitor;

import team.hedonism.Main;

public class Fork {
    private boolean availability = true;

    private int id;

    public Fork(int id) {
        this.id=id;
    }

    public void take() {
        long t = System.nanoTime();
        if (Main.DEBUG) {
            System.out.println(t + ": Fork " + id + " is taken");
        }
    }

    public void put() {
        long t = System.nanoTime();
        if (Main.DEBUG) {
            System.out.println(t + ": Fork " + id + " is put");
        }
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
